package com.example.up2061391;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    // declare server and global (public) variables
    Server server;
    public int nextID;
    public Node node;
    public String imgNum;
    public MediaPlayer audio;
    public int yOnClick; // sets 1 if yes and 0 if no

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // locks to stop landscape rotation

        ActionBar actionBar; // link to actionbar to change colour
        actionBar = getSupportActionBar();
        ColorDrawable actionColour = new ColorDrawable(getResources().getColor(R.color.white));
        actionBar.setBackgroundDrawable(actionColour);

        InputStream file = getResources().openRawResource(R.raw.datafile); // links to csv file

        // set up and create server with try/catch as throws exception
        try {
            server = new Server(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // sets all components to name then links them via xml file
        TextView titleText;
        TextView desText;
        TextView queText;
        ImageView recipeImg;
        Button yesButton;
        Button noButton;
        Button restartButton;
        Button backButton;

        titleText = findViewById(R.id.titleText);
        desText = findViewById(R.id.descriptionText);
        queText = findViewById(R.id.questionText);
        recipeImg = findViewById(R.id.recipeDisplay);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        restartButton = findViewById(R.id.restartButton);
        backButton = findViewById(R.id.backButton);

        // initally hides 2 buttons as can't use at start
        restartButton.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);

        // sets text title
        String title = "What to cook?";
        titleText.setText(title);

        // sets first node to 1 for initial description/question text
        node = server.searchNode(1);
        desText.setText(node.getDescription());
        queText.setText(node.getQuestion());

        yesButton.setOnClickListener(new View.OnClickListener() { // yes button clicked
            public void onClick(View v){
                backButton.setVisibility(View.VISIBLE);
                yOnClick = 1;
                nextID = node.getYesID(); // sets to yes to link next node
                node = server.searchNode(nextID); // grabs next input

                if(node.getYesID() == 1 && node.getNoID() == 1){ // to check if loop back to beginning to show restart button
                    desText.setText(node.getDescription());
                    queText.setVisibility(View.GONE); // hide question as none for end nodes
                    restartButton.setVisibility(View.VISIBLE);
                    yesButton.setVisibility(View.GONE);
                    noButton.setVisibility(View.GONE);
                    backButton.setVisibility(View.GONE);
                } else {
                    desText.setText(node.getDescription());
                    queText.setText(node.getQuestion());
                }

                // links to media player to play sounds based on node name
                // sets image to relevant node via name (need img before as cant have img starting numerical)
                if(node.getID() == 30) {
                    audio = MediaPlayer.create(MainActivity.this, R.raw.fishfriendaudio);
                    audio.start();
                    recipeImg.setImageResource(getResources().getIdentifier("startimg", "drawable", getPackageName()));
                } else if(node.getID() == 76){
                    audio = MediaPlayer.create(MainActivity.this, R.raw.gunaudio);
                    audio.start();
                    imgNum = "img" + node.getID();
                    recipeImg.setImageResource(getResources().getIdentifier(imgNum,"drawable",getPackageName()));
                } else if(node.getID() == 84){
                    audio = MediaPlayer.create(MainActivity.this, R.raw.tacoaudio);
                    audio.start();
                    imgNum = "img" + node.getID();
                    recipeImg.setImageResource(getResources().getIdentifier(imgNum,"drawable",getPackageName()));
                } else if (node.getID() == 44) {
                    audio = MediaPlayer.create(MainActivity.this, R.raw.weirdaudio);
                    audio.start();
                    imgNum = "img" + node.getID();
                    recipeImg.setImageResource(getResources().getIdentifier(imgNum, "drawable", getPackageName()));
                } else if (node.getID() > 44) {
                    audio = MediaPlayer.create(MainActivity.this, R.raw.foodartaudio);
                    audio.start();
                    imgNum = "img" + node.getID();
                    recipeImg.setImageResource(getResources().getIdentifier(imgNum,"drawable",getPackageName()));
                } else {
                    recipeImg.setImageResource(getResources().getIdentifier("startimg","drawable",getPackageName()));
                }
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() { // when no button clicked
            public void onClick(View v){
                backButton.setVisibility(View.VISIBLE);
                yOnClick = 0;
                nextID = node.getNoID(); // links to get no for next node
                node = server.searchNode(nextID); // grabs next input
                desText.setText(node.getDescription());

                if(node.getYesID() == 1 && node.getNoID() == 1){ // to check if loop back to beginning to show restart button
                    desText.setText(node.getDescription());
                    queText.setVisibility(View.GONE); // hide question as none for end nodes
                    restartButton.setVisibility(View.VISIBLE);
                    yesButton.setVisibility(View.GONE);
                    backButton.setVisibility(View.GONE);
                    noButton.setVisibility(View.GONE);
                } else {
                    desText.setText(node.getDescription());
                    queText.setText(node.getQuestion());
                }

                // links to media player to play sounds based on node name
                // sets image to relevant node via name (need img before as cant have img starting numerical)
                if(node.getID() == 65){
                    audio = MediaPlayer.create(MainActivity.this, R.raw.soupaudio);
                    audio.start();
                    imgNum = "img" + node.getID();
                    recipeImg.setImageResource(getResources().getIdentifier(imgNum,"drawable",getPackageName()));
                } else if(node.getID() == 67){
                    audio = MediaPlayer.create(MainActivity.this, R.raw.blenderaudio);
                    audio.start();
                    imgNum = "img" + node.getID();
                    recipeImg.setImageResource(getResources().getIdentifier(imgNum,"drawable",getPackageName()));
                } else if (node.getID() == 46) {
                    audio = MediaPlayer.create(MainActivity.this, R.raw.weirdaudio);
                    audio.start();
                    imgNum = "img" + node.getID();
                    recipeImg.setImageResource(getResources().getIdentifier(imgNum, "drawable", getPackageName()));
                } else if (node.getID() == 61) {
                    audio = MediaPlayer.create(MainActivity.this, R.raw.moneyaudio);
                    audio.start();
                    imgNum = "img" + node.getID();
                    recipeImg.setImageResource(getResources().getIdentifier(imgNum, "drawable", getPackageName()));
                } else if (node.getID() > 44) {
                    audio = MediaPlayer.create(MainActivity.this, R.raw.foodartaudio);
                    audio.start();
                    imgNum = "img" + node.getID();
                    recipeImg.setImageResource(getResources().getIdentifier(imgNum,"drawable",getPackageName()));
                } else {
                    recipeImg.setImageResource(getResources().getIdentifier("startimg","drawable",getPackageName()));
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() { // when restart clicked
            public void onClick(View v){
                audio.stop(); // stops audio if button clicked before its finished

                node = server.searchNode(1); // sets node back to 1 for restart

                queText.setVisibility(View.VISIBLE); // shows and hides relevant buttons
                restartButton.setVisibility(View.GONE);
                yesButton.setVisibility(View.VISIBLE);
                noButton.setVisibility(View.VISIBLE);

                // sets img and text back to start question
                recipeImg.setImageResource(getResources().getIdentifier("startimg","drawable",getPackageName()));
                desText.setText(node.getDescription());
                queText.setText(node.getQuestion());
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() { // when back button clicked
            public void onClick(View v){
                node = server.searchPrevNode(yOnClick, nextID); // passes current node ID and click (weather last node was set via yes or no) to return which node it came from
                nextID = node.getID(); // sets nextID to node set above
                desText.setText(node.getDescription());
                queText.setText(node.getQuestion());
                if(node.getID() == 1){ // if back to beginning hide back button
                    backButton.setVisibility(View.GONE);
                }
            }
        });
    }
}