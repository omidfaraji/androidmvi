package com.faraji.challenge.vira.ui.mainpage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faraji.challenge.vira.R
import com.faraji.challenge.vira.domain.model.Game
import com.faraji.challenge.vira.presentation.model.*
import kotlinx.android.synthetic.main.game_result_live_row.view.*
import kotlinx.android.synthetic.main.game_result_live_row.view.imgFirstTeam
import kotlinx.android.synthetic.main.game_result_live_row.view.imgSecondTeam
import kotlinx.android.synthetic.main.game_result_live_row.view.txtFirstTeam
import kotlinx.android.synthetic.main.game_result_live_row.view.txtSecondTeam
import kotlinx.android.synthetic.main.game_result_normal_row.view.*

class GameResultAdapter(
    val context: Context,
    items: List<GameAdapterItem> = mutableListOf()
) : RecyclerView.Adapter<GameResultAdapter.GameResultBaseHolder>() {

    var items: MutableList<GameAdapterItem> = items.toMutableList()
        set(value) {
            if (value == field)
                return
            field = value
            notifyDataSetChanged()
        }

    open inner class GameResultBaseHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        open fun bind(game: Game, position: Int) {
            itemView.tag = position
            itemView.txtFirstTeam.text = game.firstTeam.name
            itemView.imgFirstTeam.setImageResource(game.firstTeam.logo)
            itemView.txtSecondTeam.text = game.secondTeam.name
            itemView.imgSecondTeam.setImageResource(game.secondTeam.logo)
        }
    }

    inner class LiveHolder(itemView: View) : GameResultBaseHolder(itemView) {

        override fun bind(game: Game, position: Int) {
            super.bind(game, position)
            itemView.txtMinuets.text = "${game.spendTime.toString()}\""
            itemView.txtResult.text = game.result

        }
    }

    inner class FinishedHolder(itemView: View) : GameResultBaseHolder(itemView) {

        override fun bind(game: Game, position: Int) {
            super.bind(game, position)
            itemView.txtResult.text = game.result
        }
    }

    inner class CanceledHolder(itemView: View) : GameResultBaseHolder(itemView)

    inner class NormalHolder(itemView: View) : GameResultBaseHolder(itemView) {

        override fun bind(game: Game, position: Int) {
            super.bind(game, position)
            itemView.txtTime.text = game.time
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameResultBaseHolder {
        val inflater = LayoutInflater.from(context)
        return when (viewType) {
            TYPE_LIVE -> {
                val view = inflater.inflate(R.layout.game_result_live_row, parent, false)
                LiveHolder(view)
            }
            TYPE_CANCELED -> {
                val view =
                    inflater.inflate(R.layout.game_result_canceled_row, parent, false)
                CanceledHolder(view)
            }
            TYPE_FINISHED -> {
                val view =
                    inflater.inflate(R.layout.game_result_finished_row, parent, false)
                FinishedHolder(view)
            }
            TYPE_NORMAL -> {
                val view = inflater.inflate(R.layout.game_result_normal_row, parent, false)
                NormalHolder(view)
            }
            else -> throw NotImplementedError("type $viewType is not valid adapter type")

        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].type

    override fun onBindViewHolder(holder: GameResultBaseHolder, position: Int) =
        holder.bind(items[position].game, position)


}