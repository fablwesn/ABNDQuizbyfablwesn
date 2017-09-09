# ABNDQuizbyfablwesn

Quiz App for ABND by Google & Udacity - 3rd Project Submission

## Preview

- Animated GIFs

  - [CreateUser Wizard](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_wizard.gif)
  - [Quiz Question](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_correct_answer.gif) - including correct answer
  - [Wrong Answer entered](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_wrong_answer.gif)
  - [Jokers used](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_joker.gif)
  - [Info Menu](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_menu_info.gif) - also displayed immediately when a user logs in for the first time
  - [Main Menu](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_menu_menu.gif)
  
  ** SPOILER **
  - [Statistics Menu](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_menu_statistics.gif)
  - [Hidden Question](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_secret_question.gif) - contains the CheckBox question with multiple correct answers
  - [Cheat Mode](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_cheat_mode.gif) - contains the EditText question 

- static PNGs

![Preview of Start Page](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_abndquiz_startpage.PNG)

![Preview of Quiz Page](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_abndquiz_quiz.PNG)
![Preview of Menu Page](https://raw.githubusercontent.com/fablwesn/ABNDQuizbyfablwesn/master/preview/preview_abndquiz_menu.PNG)

## Author

* **Darijo Barucic** - *Initial work* - [fablwesn](https://github.com/fablwesn)

## Installing

You need the latest version of Android Studio and an emulator/device with minimum API Level 21.

To have everything displayed correctly, please fulfill following requirements:

- Emulating in Android Studio
  - install SDK for min. API Level 21
  - use Nexus 6 for emulating and displaying the preview

- Running on Hardware Device
  - have at least API Level 21 installed
  - only tested on Samsung Galaxy S6, chose a device with similar display (560dpi)

## Game Rules

### General

- There are 4 Quiz-Categories to chose from. Each contains 10 questions, 5 easy and 5 hard ones.
- 2 Jokers are available for use for **each** question. The Question-PointValue is reduced on usage. (DoubleTime Joker & 50/50 Joker)
- To win the game, answer all 40 questions correctly!
- If you answer incorrectly or the time runs out, you lose and the question will reset and start from the beginning.
- After each correctly answered question, you get to chose a bonus question. Jokers disabled! ** these are not required to win the game **

### Scores

- answering each question correctly adds points to your current score 
  - easy question:  150 points
  - hard question:  300 points
  - bonus question: basepoints x 2 (150/300 x 2)
- using a Joker reduces the points you will get, if answered correctly
  - using the DoubleTime Joker halfes the base point value of the question being used on
  - using the 50/50 Joker reduces the base point value of the question being used on by a third
  - _Example: Both Jokers used on an easy question would look like this: 150 - (150/3) - (150/2) scoring the user 25 points on answering correctly. Try to avoid using both jokers on one question to achieve big highscores!_
  
### Secrets

  - Hidden Question, when found and answered correctly, you receive 50 Achievement Points, unlocking the "Statistics" tab in the menu
  - Cheat Mode, does the same as above but is hidden somewhere else and can only be used if the hidden question hasn't been answered already

## Acknowledgments

![freepik](http://www.freepik.com/) - Image Assets mainly used from there, most were edited by me additionally 

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE.md](LICENSE.md) file for details


