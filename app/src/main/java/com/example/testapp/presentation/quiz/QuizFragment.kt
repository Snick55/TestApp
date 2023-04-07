package com.example.testapp.presentation.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testapp.databinding.QuizFragmentBinding
import com.example.testapp.model.entities.Question
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private lateinit var binding: QuizFragmentBinding
    private val viewModel by viewModels<QuizViewModel>()
    private var pointValue = 0
    private lateinit var question: Question
    private var countDownCustom:CountDownCustom? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QuizFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentContainer.visibility = View.GONE

        viewModel.currentQuestion.observe(viewLifecycleOwner) { event ->
            event.get()?.let {
                question = it
                binding.contentContainer.visibility = View.VISIBLE
                binding.title.text = it.title
                binding.firstAnswer.text = it.answers[0].answer
                binding.secondAnswer.text = it.answers[1].answer
                binding.thirdAnswer.text = it.answers[2].answer
            }
        }

        binding.firstAnswer.setOnClickListener {
            if (isAnswerCurrent(binding.firstAnswer.text.toString())) {
                viewModel.updatePoints(pointValue)
            }
            viewModel.nextQuestion()
        }
        binding.secondAnswer.setOnClickListener {
            if (isAnswerCurrent(binding.secondAnswer.text.toString())) {
                viewModel.updatePoints(pointValue)
            }
            viewModel.nextQuestion()
        }
        binding.thirdAnswer.setOnClickListener {
            if (isAnswerCurrent(binding.thirdAnswer.text.toString())) {
                viewModel.updatePoints(pointValue)
            }
            viewModel.nextQuestion()
        }

        binding.easyBtn.setOnClickListener {
            pointValue = 1
            binding.difficultyContainer.visibility = View.GONE
            viewModel.getQuestions("easy")
            startTimer()

        }

        binding.mediumBtn.setOnClickListener {
            pointValue = 2
            binding.difficultyContainer.visibility = View.GONE
            viewModel.getQuestions("medium")
            startTimer()
        }

        binding.hardBtn.setOnClickListener {
            pointValue = 3
            binding.difficultyContainer.visibility = View.GONE
            viewModel.getQuestions("hard")
            startTimer()
        }
    }

    private fun startTimer(){
        binding.timerTv.visibility = View.VISIBLE
        countDownCustom = CountDownCustom(binding.timerTv){
            findNavController().popBackStack()
        }.start() as CountDownCustom?
    }

    private fun isAnswerCurrent(answer: String): Boolean {
        return question.currentAnswer == answer
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownCustom?.cancel()
    }

}