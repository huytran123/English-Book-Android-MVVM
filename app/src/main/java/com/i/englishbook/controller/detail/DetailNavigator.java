package com.i.englishbook.controller.detail;

import com.i.englishbook.model.ModePlay;
import com.i.englishbook.model.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huytran on 9/26/2017.
 */

public interface DetailNavigator {
    void getSentencesComplete(List<Sentence> sentences);

    void getSentencesError(String mess);

    void playSentence(int sentence);

    void stopSentence();

    void sentenceClick(int index);

    void onClickSpeech(int index);

    void onClickSpeechSlow(int index);
}
