package com.example.statethefacts;

import java.util.List;

public class GameQuestionPresenter {
    private GameActivity activity;
    private GameQuestion question;
    private List<State> states;
    private GameResult gameResult;
    private GameAnswer lastAnswer;
    private GameType gameType;

    public GameQuestionPresenter(GameActivity activity, GameType gameType){
        this.activity = activity;

        GetFacts gf = new GetFacts();
        states = gf.Fetch(activity).states;
        this.gameType = gameType;
        gameResult = new GameResult(gameType);
    }

    public GameQuestion getNextQuestion(QuestionsType questionsType) {
        question = new GameQuestion(states,questionsType);
        question.generate("Texas", gameResult.getGameType());
        return question;
    }

    public void submitAnswer(String submittedAnswer) {
        lastAnswer = new GameAnswer(gameResult.getGameId(), question.getState(), question.getAnswer(), submittedAnswer, question.getQuestionType());
        gameResult.AddAnswer(lastAnswer);
        activity.flipCard();
    }


    public GameQuestion getQuestion(){
        return question;
    }

    public GameAnswer getLastAnswer() {
        return lastAnswer;
    }

    public GameType getGameType() { return gameType; }
}
