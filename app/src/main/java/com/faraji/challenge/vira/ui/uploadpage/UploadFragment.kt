package com.faraji.challenge.vira.ui.uploadpage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.faraji.challenge.vira.R
import com.faraji.challenge.vira.presentation.uploadpage.UploadIntent
import com.faraji.challenge.vira.presentation.uploadpage.UploadViewModel
import com.faraji.challenge.vira.presentation.uploadpage.UploadViewState
import com.faraji.challenge.vira.ui.base.IntentFragment
import com.faraji.challenge.vira.ui.extension.longToast
import kotlinx.android.synthetic.main.fragment_upload.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.File
import java.io.IOException


class UploadFragment : IntentFragment<UploadIntent, UploadViewState, UploadViewModel>() {

    override val viewModel: UploadViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_upload, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSelectVideo.setOnClickListener {
            showVideoChooserDialog()
        }
    }

    private fun showVideoChooserDialog() {
        val options = arrayOf<CharSequence>(
            getString(R.string.fromCamera),
            getString(R.string.fromGallery)

        )
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.videoUpload))
        builder.setItems(options) { dialog, item ->
            when {
                options[item] == getString(R.string.fromCamera) -> {
                    val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
                    val f = File(
                        requireContext().getExternalFilesDir(null), "temp.mp4"
                    )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f))
                    startActivityForResult(intent, 1)
                }
                options[item] == getString(R.string.fromGallery) -> {
                    val intent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    )
                    intent.type = "video/*"
                    startActivityForResult(intent, 2)
                }
            }
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                val fileDir = requireContext().getExternalFilesDir(null) ?: return
                var videoFile: File? = null
                for (file in fileDir.listFiles()) {
                    if (file.name == "temp.mp4") {
                        videoFile = file
                        break
                    }
                }
                Timber.d("SelectedVideoPathCamera %s", videoFile)
                try {
                    uploadVideo(videoFile ?: return)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else if (requestCode == 2) {
                val selectedImage = data?.data ?: return
                val filePath = arrayOf(MediaStore.Video.Media.DATA)
                val c = requireContext().contentResolver.query(
                    selectedImage, filePath,
                    null, null, null
                ) ?: return
                c.moveToFirst()
                val columnIndex: Int = c.getColumnIndex(filePath[0])
                val videoPath: String = c.getString(columnIndex)
                c.close()
                Timber.d("SelectedVideoPath %s", videoPath)
                try {
                    uploadVideo(File(videoPath))
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override suspend fun handleState(state: UploadViewState) {
        progressBar.isVisible = state.loading
        when (state) {
            is UploadViewState.UploadVideoUiModel -> {
                when (state) {
                    is UploadViewState.UploadVideoUiModel.Success -> {
                        longToast(getString(R.string.videoUploadSuccess))
                    }
                    is UploadViewState.UploadVideoUiModel.Failed -> {
                        longToast(getString(state.message))
                    }
                }
            }
        }
    }


    private fun uploadVideo(videoFile: File) {
        sendIntent(UploadIntent.UploadVideoIntent(videoFile))
    }


}