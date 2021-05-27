<p align="center">
  <img src="https://github.com/akshat19231/AP_Project_ColorSwitch/blob/master/src/assets/heading.png">

<hr>


<p align="center" style="font-family:cursive">A popular mobile game closely replicated to be made available to be played on Windows/MacOS </p>

:video_game: Link to Gameplay!

## <img src="https://github.com/akshat19231/AP_Project_ColorSwitch/blob/master/src/assets/rings_preview_rev_1.png" alt="rings_preview_rev_1"  width="30px" align="left"/> Contents:

1) About

2) How to Run?

3) Snapshots

4) Features + Bonus

<hr>

## <img src="https://github.com/akshat19231/AP_Project_ColorSwitch/blob/master/src/assets/rings_preview_rev_1.png"  width="30px" align="left"/> About:

● The technology/language behind the project is JavaFX, a subsidiary of the famous language Java, which is  mainly used for making such games and GUIs (Graphical User Interfaces).  

● A single player game where, as the player progresses, the difficulty of the game increases, that is, the rotation speed of the obstacles  increase and the radius in case of circles decreases. 

<hr>

## <img src="https://github.com/akshat19231/AP_Project_ColorSwitch/blob/master/src/assets/rings_preview_rev_1.png" alt="rings_preview_rev_1"  width="30px" align="left"/> How to Run?

* Clone the project into your desired directory by opening command line and entering ```git clone https://github.com/akshat19231/AP_Project_ColorSwitch```

* Open the project as an existing one, then configure the libraries.

* To configure the JavaFX libraries, go to  `File > Project Structure > Project Settings > Libraries`,

  click on the + button, and select the path to the JavaFX 'lib' folder.

* Now to configure the VM options, go to  `Run > Edit Configurations` and add `--module-path
  "C:\Users\user\Downloads\javafx-sdk-15.0.1\lib"
  --add-modules=javafx.controls,javafx.fxml,javafx.media` under VM options.

* Select build, then go to `src/sample/Main.java` and run it.

<hr>

## <img src="https://github.com/akshat19231/AP_Project_ColorSwitch/blob/master/src/assets/rings_preview_rev_1.png" alt="rings_preview_rev_1"  width="30px" align="left"/>Snapshots
<p align="center">
  <img src="https://github.com/akshat19231/AP_Project_ColorSwitch/blob/master/src/assets/snapshots.jpeg">

<hr>

## <img src="https://github.com/akshat19231/AP_Project_ColorSwitch/blob/master/src/assets/rings_preview_rev_1.png" alt="rings_preview_rev_1" width="30px" align="left"/>Features
* The game is based on a ball, controlled by us and is led through a path of different moving obstacles and a few more objects. Whenever we pass through an obstacle, we gain points. The game objects other than the obstacles are colour wheels and stars. 
* Now coming to the mechanics of the game; we use the spacebar to give the ball an upwards jump. The ball has a colour, which should have the same colour as the part through which it is passing in order to pass through the obstacle. 
* The colour wheels spawn randomly in the path, which will change the colour of the ball. Make sure to check which colour it is changed into before hitting an obstacle!
* Crashed into a different colour while having a good run? Well, we’ve got you covered in that case too! Use the stars collected in the previous runs and resume from the place where you fell off!
<hr>


## <img src="https://github.com/akshat19231/AP_Project_ColorSwitch/blob/master/src/assets/rings_preview_rev_1.png" alt="rings_preview_rev_1" width="30px" align="left"/>Bonus
* Dynamic leaderboard is available too! Beat others to a high score they can’t catch up to.
* Music is played during every run of yours. Some amazing music alongside a game is always fun! 
* In the original mode, we control the ball’s movement, and make them pass through the obstacles, but we thought of adding something more to this. We added a unique game mode, in which the ball is moving upwards, and we control the rotation of the obstacles by the ‘A’ and ‘D’ keys for left and right rotation respectively.
*The game mode can be changed by clicking the reverse button available on the home screen. The leaderboard has the entries with the suffix (Reverse) added to the games played in this mode.
<hr>
