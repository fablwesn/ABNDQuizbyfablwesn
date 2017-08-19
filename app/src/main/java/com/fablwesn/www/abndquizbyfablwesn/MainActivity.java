package com.fablwesn.www.abndquizbyfablwesn;

/* *************************************************************************************************
 * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
 * | Darijo Barucic                                                                                |
 * | fablwesn@gmail.com                                                                            |
 * | Vienna, Austria                                                                               |
 * |_______________________________________________________________________________________________|
 * |                                                                                               |
 * | Oh my god, it's finished. It's been a really long and hard adventure but I'm so proud of the  |
 * | end-result. It's only the third app I've done on my own and I hardly can't believe how far I  |
 * | have gotten, thanks to you guys! :)                                                           |
 * | It was really scary seeing myself "evolve" while coding, looking at 2 days old code always    |
 * | made me think "Why did I think so complicated, would have been easier like that" :D           |
 * |                                                                                               |
 * | I'm sorry that I've overdone it quite a bit, especially because I only used one activity.     |
 * | I've tried to make it as readable as possible. I didn't want to use more activities           |
 * | because the next lesson is all about multi-screen apps...my mistake. Sorry :x                 |
 * |                                                                                               |
 * | I am especially proud of the whole system behind the questions being displayed and managed    |
 * | Also the TimeJoker was pretty hard to implant, because until I realized, that you can't       |
 * | change the time of an existing CountdownTimer, half a day passed~                             |
 * | But I love how it works now!                                                                  |
 * |                                                                                               |
 * | Performance seems pretty stable, CPU never went beyond 3% usage - but peak memory usage was   |
 * | at 115mb with 15mb free (what does this exactly mean?)                                        |
 * | And a question, irrelevant for this app, but is it possible to use the RAM of the GPU? It's   |
 * | usage was at 0 the whole time, could i theoretically take some of the memory and store it in  |
 * | the GPU instead?                                                                              |
 * |                                                                                               |
 * | The Statistics Menu is locked, until you get 50 AchievementPoints, either find the            |
 * | SecretQuestion (tap on ABND Header with brain on startPage or InfoPage) or unlock cheat       |
 * | mode, tapping 5 times on your avatar in the GameScreen StartPage (only available if the       |
 * | secret question hasn't been answered already)                                                 |
 * |                                                                                               |
 * |                        ! RadioButtons are found in the MainQuiz !                             |
 * |                      ! CheckBoxes found within the SecretQuestion !                           |
 * |                     ! TextAnswer triggered when activating cheat mode !                       |
 * |                                                                                               |
 * | EMULATED ON NEXUS 6                                                                           |
 * | TESTED ON SAMSUNG GALAXY S6                                                                   |
 * |                                                                                               |
 * | graphics used are nearly all from http://www.freepik.com/, I've just edited them to fit my    |
 * | needs.                                                                                        |
 * |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
 **************************************************************************************************/

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static android.widget.Toast.makeText;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.btn_feedback_menu;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.btn_help_menu_top;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.btn_open_menu_top;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.btn_quit_game_menu;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.btn_sore_value_high_game_screen;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.btn_sore_value_low_game_screen;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.btn_statistics_menu;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.img_btn_category_a_game;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.img_btn_category_b_game;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.img_btn_category_d_game;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.img_btn_category_n_game;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.img_btn_secret_question;
import static com.fablwesn.www.abndquizbyfablwesn.R.id.space_radio_buttons;

public class MainActivity extends AppCompatActivity {

/* ***********************************************
* |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
* |  Global Variables  (names starting with '_')  |
* |_____________________________________________*/

    /**
     * SharedPreferences used throughout the game to store values for the three userProfiles.
     */

    // used as the name for the sharedPrefs
    private static final String USER_ONE_PROFILE = "com.fablwesn.abndquiz.USER_ONE_PROFILE";
    private static final String USER_TWO_PROFILE = "com.fablwesn.abndquiz.USER_TWO_PROFILE";
    private static final String USER_THREE_PROFILE = "com.fablwesn.abndquiz.USER_THREE_PROFILE";

    // keyValue names
    private static final String USER_CREATED_BOOL = "USER_CREATED"; // which user is being created, 1-3
    private static final String USER_NAME_STRING = "USER_NAME"; // the name the user entered and that will be displayed for his profile
    private static final String USER_IS_MALE_BOOL = "USER_GENDER_MALE";
    private static final String USER_IS_FEMALE_BOOL = "USER_GENDER_FEMALE";
    private static final String USER_AVATAR_INT = "USER_AVATAR"; // chosen avatar, 0 = empty, 1-6 are the drawables to choose from
    private static final String USER_HIGHSCORE_INT = "USER_HIGHSCORE";
    private static final String USER_ACHIEVEMENT_POINTS_INT = "USER_ACHIEVEMENT_POINTS";
    private static final String USER_HAS_PLAYED_BEFORE_BOOL = "USER_HAS_PLAYED_ONCE"; //determines if it's the first time starting a game with this profile
    private static final String USER_TRIGGERED_SECRET_QUESTION_BOOL = "USER_TRIGGERED_SECRET_QUESTION"; //if the secret question has been triggered before loading/creating an user
    private static final String USER_ANSWERED_SECRET_QUESTION_BOOL = "USER_ANSWERED_SECRET_QUESTION"; //if true, add AchievementPoints for user
    //for statistics
    private static final String USER_HIGHEST_SCORE_STREAK_INT = "USER_HIGHEST_STREAK"; // record for how many answer the user has answered correctly in a row
    private static final String USER_TIMES_WON_INT = "USER_TOTAL_WINS"; // how often the user has reached the end of the quiz
    private static final String USER_TIMES_LOGGED_IN_INT = "USER_LOGINS"; // how often this user has been loaded
    //how often which categories has been answered right or wrong (and has been selected in total with right + wrong)
    private static final String USER_BONUS_HIGHEST_STREAK_INT = "USER_BONUS_STREAK"; // userMax of how many bonus questions have been answered in a row
    private static final String USER_BONUS_QUESTION_RIGHT_INT = "USER_BONUS_RIGHT"; // how often the bonus question has been answered correctly
    private static final String USER_BONUS_QUESTION_WRONG_INT = "USER_BONUS_WRONG"; // how often the bonus question has been answered incorrectly
    private static final String USER_BONUS_QUESTION_TIMEOUT_INT = "USER_BONUS_TIMEOUT"; //how often the user timedOut in a bonus question
    //animals
    private static final String USER_QUANT_CHOOSE_EASY_ANIMALS_RIGHT_INT = "USER_EASY_ANIMALS_RIGHT";
    private static final String USER_QUANT_CHOOSE_EASY_ANIMALS_WRONG_INT = "USER_EASY_ANIMALS_WRONG";
    private static final String USER_QUANT_CHOOSE_HARD_ANIMALS_RIGHT_INT = "USER_HARD_ANIMALS_RIGHT";
    private static final String USER_QUANT_CHOOSE_HARD_ANIMALS_WRONG_INT = "USER_HARD_ANIMALS_WRONG";
    private static final String USER_QUANT_CHOOSE_EASY_ANIMALS_TIMEOUT_INT = "USER_EASY_ANIMALS_TIMEOUT";
    private static final String USER_QUANT_CHOOSE_HARD_ANIMALS_TIMEOUT_INT = "USER_HARD_ANIMALS_TIMEOUT";
    //books
    private static final String USER_QUANT_CHOOSE_EASY_BOOKS_RIGHT_INT = "USER_EASY_BOOKS_RIGHT";
    private static final String USER_QUANT_CHOOSE_EASY_BOOKS_WRONG_INT = "USER_EASY_BOOKS_WRONG";
    private static final String USER_QUANT_CHOOSE_HARD_BOOKS_RIGHT_INT = "USER_HARD_BOOKS_RIGHT";
    private static final String USER_QUANT_CHOOSE_HARD_BOOKS_WRONG_INT = "USER_HARD_BOOKS_WRONG";
    private static final String USER_QUANT_CHOOSE_EASY_BOOKS_TIMEOUT_INT = "USER_EASY_BOOKS_TIMEOUT";
    private static final String USER_QUANT_CHOOSE_HARD_BOOKS_TIMEOUT_INT = "USER_HARD_BOOKS_TIMEOUT";
    //netflix
    private static final String USER_QUANT_CHOOSE_EASY_NETFLIX_RIGHT_INT = "USER_EASY_NETFLIX_RIGHT";
    private static final String USER_QUANT_CHOOSE_EASY_NETFLIX_WRONG_INT = "USER_EASY_NETFLIX_WRONG";
    private static final String USER_QUANT_CHOOSE_HARD_NETFLIX_RIGHT_INT = "USER_HARD_NETFLIX_RIGHT";
    private static final String USER_QUANT_CHOOSE_HARD_NETFLIX_WRONG_INT = "USER_HARD_NETFLIX_WRONG";
    private static final String USER_QUANT_CHOOSE_EASY_NETFLIX_TIMEOUT_INT = "USER_EASY_NETFLIX_TIMEOUT";
    private static final String USER_QUANT_CHOOSE_HARD_NETFLIX_TIMEOUT_INT = "USER_HARD_NETFLIX_TIMEOUT";
    //digital
    private static final String USER_QUANT_CHOOSE_EASY_DIGITAL_RIGHT_INT = "USER_EASY_DIGITAL_RIGHT";
    private static final String USER_QUANT_CHOOSE_EASY_DIGITAL_WRONG_INT = "USER_EASY_DIGITAL_WRONG";
    private static final String USER_QUANT_CHOOSE_HARD_DIGITAL_RIGHT_INT = "USER_HARD_DIGITAL_RIGHT";
    private static final String USER_QUANT_CHOOSE_HARD_DIGITAL_WRONG_INT = "USER_HARD_DIGITAL_WRONG";
    private static final String USER_QUANT_CHOOSE_EASY_DIGITAL_TIMEOUT_INT = "USER_EASY_DIGITAL_TIMEOUT";
    private static final String USER_QUANT_CHOOSE_HARD_DIGITAL_TIMEOUT_INT = "USER_HARD_DIGITAL_TIMEOUT";

    // sharedPref objects
    SharedPreferences _userOnePrefs;
    SharedPreferences _userTwoPrefs;
    SharedPreferences _userThreePrefs;

    //the user currently playing
    SharedPreferences _userPlaying;

    // corresponding editors
    SharedPreferences.Editor _editUserOneSave;
    SharedPreferences.Editor _editUserTwoSave;
    SharedPreferences.Editor _editUserThreeSave;

    SharedPreferences.Editor _editUserPlaying;

    /**
     * StartPage/Wizard variables used to temporally store values before sending them off to the sharedPrefs, so a
     * user is only created after the end of the createUserWizard
     */

    // When the create user button is pressed, look which profile pressed it and store it inside (1-3, top to bottom)
    private int _userToCreate;
    // entered username to store for profile _userToCreate
    private String _userName;
    // selected gender to store for profile _userToCreate
    private boolean _isMale = false;
    private boolean _isFemale = false;
    // chosen avatar to store for profile _userToCreate, 1-6, male or female version depends on genderBool
    private int _avatarSelected = 1;
    //if the secret question has been triggered beforehand
    private boolean _secretQuestionTriggered = false;

    /**
     * globals used for the first 2 AppPages (startPage and createUserWizard)
     */

    // startPage and Wizard ParentViews
    View _startPageLayout = null;
    View _userWizardLayout = null;
    Spinner _spinGender = null;
    GridLayout _choseAvatarGrid = null;

    //user AP and HS TextView
    TextView _userMenuAP;
    TextView _userMenuHS;

    /**
     * for navigating through the startPage and the Wizard with its subPages
     * 0 = startPage
     * 1 = Wizard.enterNamePage
     * 2 = Wizard.selectGenderPage
     * 3 = Wizard.choseAvatarPage
     */
    private int _currentWizardPage;

    /*--------------------------------------------------------------------------------------------*/

    /**
     * global Views used  on the GameScreenSubPages and the MenuSubPages
     */

    /* ParentLayouts */
    //Bottom frame containing the body text and menus
    View _relLayMenuFrameParent = null;
    //holds the progressBar and Text for the time left for the answer
    View _linLayTimeIndicator = null;
    //parent of all answerRadioButtons
    View _relLayRadioButtonParent = null;
    //parent of all answerCheckBoxes
    View _relLayCheckBoxParent = null;
    //Statistics Menu Parent
    View _statisticsMenuParent = null;
    //Feedback Menu Parent
    View _feedbackMenuParent = null;

    /*
        TextViews
     */

    //TitleText on top, outside the question process
    TextView _titleText;
    //BodyText outside the question process
    TextView _bodyText;
    //shortTimerText counting from 3 to 0
    TextView _shortCountdownText;
    //timeTextView on QuestionPage, next to Progressbar
    TextView _timeLeftToAnswerText; //currentPointsText
    TextView _currentUserPointsText;
    //question text
    TextView _questionText;

    /*
        ImageViews
     */

    //Professor HeaderImage
    ImageView _professorImg;
    //shortTimerBG
    View _shortCountdownBackgroundImage;

    /*
        Buttons
     */

    //UserInputButton on the bottom
    Button _userInputBtn;
    //top menu buttons
    Button _menuTopMenu;
    Button _menuTopHelp;
    //inside MenuButtons
    View _openStatisticsMenuBtn;
    View _openOptionsMenuBtn;
    View _quitGameMenuBtn;
    //JokerButtons
    ImageButton _jokerTimeBtn;
    ImageButton _jokerChanceBtn;

    //Category Buttons
    ArrayList<View> _categoryButtons = null;

    //question radio buttons
    RadioButton _answerRadioButtonOne;
    RadioButton _answerRadioButtonTwo;
    RadioButton _answerRadioButtonThree;
    RadioButton _answerRadioButtonFour;

    //question checkBoxes
    CheckBox _answerCheckBoxOne;
    CheckBox _answerCheckBoxTwo;
    CheckBox _answerCheckBoxThree;
    CheckBox _answerCheckBoxFour;

    //ButtonStates while a question is asked
    RadioButton _hiddenByChanceJoker01;
    RadioButton _hiddenByChanceJoker02;
    RadioButton _rightAnswerButton;
    View _selectedAnswerButton;

    /*
        Misc
     */

    //prof on fire animation
    AnimationDrawable _burningPassionAnimDraw;

    //progressBar
    ProgressBar _timeLeftToAnswerBar;


    //methods and toasts used globally
    //The timer used as a time limit for every question
    CountDownTimer _countDownTimerQuestion;
    //achievementUnlocked Toast displayed after an achievement is unlocked
    Toast _achievementToast;

    /**
     * for navigating through the GameScreen SubPages
     * 00 = Info Page
     * 01 = Help Page
     * 02 = Start Page
     * 03 = CategorySelection Page
     * 04 = ScoreValueChoice Page
     * 05 = QuestionCountdown page
     * 06 = QuestionsAsked Page
     * 07 = BonusQuestionConfirmation Page
     * 08 = SecretQuestionPage
     * 09 = Menu Page
     * 10 = Menu.Statistics Page
     * 11 = Menu.Feedback Page
     */
    //stores the page currently visible
    private int _currentGamePage;
    //storing the page to return to, after leaving the menu
    private int _returnTo;
    // if user is in the menus
    private boolean _isInMenu = false;

    //index for the pages
    private static final int INFO_PAGE = 0;
    private static final int HELP_PAGE = 1;
    private static final int START_PAGE = 2;
    private static final int CATEGORY_SELECTION_PAGE = 3;
    private static final int SCORE_VALUE_CHOICE_PAGE = 4;
    private static final int PRE_QUESTION_COUNTDOWN_PAGE = 5;
    private static final int QUESTIONS_ASKED_PAGE = 6;
    private static final int BONUS_QUESTION_CONFIRMATION_PAGE = 7;
    private static final int SECRET_QUESTION_PAGE = 8;
    private static final int MENU_PAGE = 9;
    private static final int STATISTICS_MENU_PAGE = 10;
    private static final int FEEDBACK_MENU_PAGE = 11;
    private static final int CHEAT_QUESTION_PAGE = 12;

    /**
     * QuizLogic Globals
     */

    //score to add if answering correctly. if its a new Highscore will be updated to the sharedPrefs
    //value depends on scoreValue chosen, bonusQuestion yes/no and if jokers have been used (time: -30%, 50/50: -50%, bonus: +100% - all % of the baseValues (150 or 300)
    // so the order in which you use the joker, if multiple are used in one question, don't affect the scoreToAdd)
    private int _scoreToAdd;
    //score displayed with tertiary background, resets to Zero on gameOver
    private int _currentScore;

    //the category chosen for the next question
    private int _categoryChosen;
    //Question Difficulty for the next question
    private int _questionDifficultyChosen;
    //if its currently bonusQuestionTime
    private boolean _isBonusQuestion;

    // variable storing the currently chosen categorys
    private static final int BONUS_CATEGORY = 0;
    private static final int ANIMALS_CATEGORY = 1;
    private static final int BOOKS_CATEGORY = 2;
    private static final int NETFLIX_CATEGORY = 3;
    private static final int DIGITAL_CATEGORY = 4;
    // variable storing the currently chosen difficulty
    private static final int EASY_DIFFICULTY = 1;
    private static final int HARD_DIFFICULTY = 2;

    //Question ArrayLists
    //if a question is asked, it gets removed from the book instantly so it won't be asked again.
    //the books refill itself on restart/gameOver. If all books are empty (BonusBook is an exception), the user wins the game
    ArrayList<String> _animalsEasyBook;
    ArrayList<String> _animalsHardBook;
    ArrayList<String> _booksEasyBook;
    ArrayList<String> _booksHardBook;
    ArrayList<String> _netflixEasyBook;
    ArrayList<String> _netflixHardBook;
    ArrayList<String> _digitalEasyBook;
    ArrayList<String> _digitalHardBook;
    ArrayList<String> _bonusBook;

    //when to disable the scoreValueButtons and the CategoryButtons, depending on the amount of questions left in each category
    // when all catalogues are empty, all these booleans are true (except maybe the bonusBook) and the user has won the game
    private boolean _disableAnimalsEasyScore;
    private boolean _disableAnimalsHardScore;
    private boolean _disableBooksEasyScore;
    private boolean _disableBooksHardScore;
    private boolean _disableNetflixEasyScore;
    private boolean _disableNetflixHardScore;
    private boolean _disableDigitalEasyScore;
    private boolean _disableDigitalHardScore;


    // checks if its a newGame after starting or gameOver to reload the questionCatalogues and update the Score etc.
    private boolean _newGame = true;

    //numbers of questions answered before gameOver
    private int _questionNumber = 1;
    private int _bonusQuestionsAnswered;

    // if the user used the timeJoker, resets after each question
    private boolean _jokerDoubleTimeUsed;
    //checks if the answeringCountdown is running a second time after the timeJoker has been used, managing the frozen prof, the bar and the timeLeftText)
    private boolean _isSecondCountdown;
    // stores the time left before the joker was used, so the prof melts back on time and the counter starts decreasing correctly
    private int _timeLeftBeforeFreezeJoker;

    //timer used for the cheat unlocking 50 AP without having to look for the secret Question
    private boolean _cheatTimerStarted = false;
    //checks if the cheat gets activated or not, depending if its value is high enough
    private int _timesClickedCheat;



/* *************
 * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
 * |  onCreate  |
 * |__________*/

    /**
     * locks landscape mode
     * stores the sharedPrefs to be usable from the beginning throughout
     * displays the startPage and shows correct arrangement of the profiles (through sharedPrefs)
     * sets the current page to be 0 (only relevant if the user wants to create a new profile)
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation

        // get sharedPrefs reference
        // initialize sharedPreferences
        _userOnePrefs = getSharedPreferences(USER_ONE_PROFILE, Context.MODE_PRIVATE);
        _userTwoPrefs = getSharedPreferences(USER_TWO_PROFILE, Context.MODE_PRIVATE);
        _userThreePrefs = getSharedPreferences(USER_THREE_PROFILE, Context.MODE_PRIVATE);
        // initialize editors
        _editUserOneSave = _userOnePrefs.edit();
        _editUserOneSave.apply();
        _editUserTwoSave = _userTwoPrefs.edit();
        _editUserTwoSave.apply();
        _editUserThreeSave = _userThreePrefs.edit();
        _editUserThreeSave.apply();

        //initialize the layout and make sure its the first thing the user sees
        displayRootStartPage(true);

        //displays the correct profile tabs depending on sharedPrefs
        arrangeProfileTabs(findViewById(R.id.start_page_profile_one), _userOnePrefs);
        arrangeProfileTabs(findViewById(R.id.start_page_profile_two), _userTwoPrefs);
        arrangeProfileTabs(findViewById(R.id.start_page_profile_three), _userThreePrefs);

        //set pageNavigator var
        _currentWizardPage = 0;

    }

    /* *************
 * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
 * |  Navigators |
 * |___________*/

    /**
     * Navigates between startPage and the Wizard and his subPages. EndDestination is the GameScreen
     * <p>
     * pass currentPage and the next page you'd like to go to and this one will
     * show it to you
     * <p>
     * 0 = startPage
     * 1 = Wizard.enterNamePage
     * 2 = Wizard.selectGenderPage
     * 3 = Wizard.choseAvatarPage
     *
     * @param fromWhere
     * @param whereTo
     */
    private void createUserWizardNavigator(int fromWhere, int whereTo) {

        //manages hiding the fromWhere views
        switch (fromWhere) {
            case 0:
                displayRootStartPage(false);
                break;
            case 1:
                if (whereTo == 0)
                    displayUserWizardParent(false);
                break;
            case 2:
                if (whereTo == 0)
                    displayUserWizardParent(false);
                break;
            case 3:
                if (whereTo == 0)
                    displayUserWizardParent(false);
                break;
            default:
                break;
        }

        //manages displaying of the whereTo views
        switch (whereTo) {
            case 0:
                displayRootStartPage(true);
                _currentWizardPage = 0;
                break;
            case 1:
                _currentWizardPage = 1;
                arrangeCorrectWizardPage(_currentWizardPage);
                break;
            case 2:
                _currentWizardPage = 2;
                arrangeCorrectWizardPage(_currentWizardPage);
                break;
            case 3:
                _currentWizardPage = 3;
                arrangeCorrectWizardPage(_currentWizardPage);
                break;
            default:
                break;
        }
    }

    /**
     * Navigates between the gameScreens subPages.
     * <p>
     * pass currentPage and the next page you'd like to go to and this one will
     * show it to you
     * <p>
     * 00 = Info Page
     * 01 = Help Page
     * 02 = Start Page
     * 03 = CategorySelection Page
     * 04 = ScoreValueChoice Page
     * 05 = QuestionCountdown page
     * 06 = QuestionsAsked Page
     * 07 = BonusQuestionConfirmation Page
     * 08 = SecretQuestionPage
     * 09 = Menu Page
     * 10 = Menu.Statistics Page
     * 11 = Menu.Feedback Page
     *
     * @param fromWhere
     * @param whereTo
     */
    private void gameScreenNavigator(int fromWhere, int whereTo) {

        //set the currentPage to the next one being loaded
        _currentGamePage = whereTo;

        //transition logic for hiding previous page
        switch (fromWhere) {
            case INFO_PAGE:
                displayInfoPage(false);
                break;
            case HELP_PAGE:
                displayHelpPage(false);
                break;
            case START_PAGE:
                displayGameStartPage(false);
                break;
            case CATEGORY_SELECTION_PAGE:
                displayCategorySelectionPage(false);
                break;
            case SCORE_VALUE_CHOICE_PAGE:
                displayScoreValueChoicePage(false);
                break;
            case MENU_PAGE:
                displayMenuMainPage(false);
                break;
            case PRE_QUESTION_COUNTDOWN_PAGE:
                displayQuestionCountdownPage(false);
                break;
            case QUESTIONS_ASKED_PAGE:
                displayQuestionAskedPage(false);
                break;
            case SECRET_QUESTION_PAGE:
                displaySecretQuestionPage(false);
                break;
            case BONUS_QUESTION_CONFIRMATION_PAGE:
                displayBonusQuestionConfirmationPage(false);
                break;
            case STATISTICS_MENU_PAGE:
                displayStatisticsMenuPage(false);
                break;
            case FEEDBACK_MENU_PAGE:
                displayFeedbackMenuPage(false);
                break;
            case CHEAT_QUESTION_PAGE:
                displayCheatQuestion(false);
                break;
            default:
                break;
        }


        //transition logic for displaying next page
        switch (whereTo) {
            case INFO_PAGE:
                displayInfoPage(true);
                break;
            case HELP_PAGE:
                displayHelpPage(true);
                break;
            case START_PAGE:
                displayGameStartPage(true);
                break;
            case CATEGORY_SELECTION_PAGE:
                displayCategorySelectionPage(true);
                break;
            case SCORE_VALUE_CHOICE_PAGE:
                displayScoreValueChoicePage(true);
                break;
            case MENU_PAGE:
                displayMenuMainPage(true);
                break;
            case PRE_QUESTION_COUNTDOWN_PAGE:
                //set to playing
                _menuTopHelp.setAlpha(0.3f);
                _menuTopMenu.setAlpha(0.3f);
                displayQuestionCountdownPage(true);
                break;
            case QUESTIONS_ASKED_PAGE:
                displayQuestionAskedPage(true);
                break;
            case SECRET_QUESTION_PAGE:
                displaySecretQuestionPage(true);
                break;
            case BONUS_QUESTION_CONFIRMATION_PAGE:
                displayBonusQuestionConfirmationPage(true);
                break;
            case STATISTICS_MENU_PAGE:
                displayStatisticsMenuPage(true);
                break;
            case FEEDBACK_MENU_PAGE:
                displayFeedbackMenuPage(true);
                break;
            case CHEAT_QUESTION_PAGE:
                displayCheatQuestion(true);
                break;
            default:
                break;
        }

    }

/* *************************************************************************************************
  * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\
  * |                                                                                              |
  * |  StartPage                                                                                   |
  * |                                                                                              |
  * |    first Screen the user gets to see after starting the app, always                          |
  * |____________________________________________________________________________________________*/
    /* *************
    * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
    * |  displayManager  |
    * |________________*/

    /**
     * manages visibility of the whole startPage
     * <p>
     * initializes layout if it hasn't been already
     * if passing true changes visibility to visible, if it isn't already
     * if passing false changes visibility to gone, if it isn't already
     *
     * @param isVisible
     */
    private void displayRootStartPage(final boolean isVisible) {

        // initialize if it hasn't been already
        if (_startPageLayout == null) {
            _startPageLayout = findViewById(R.id.linlayout_parent_start_page);
        }

        // display or hide, depending on the parameter
        if (isVisible && _startPageLayout.getVisibility() == View.GONE) {
            _startPageLayout.setVisibility(View.VISIBLE);
        } else if (!isVisible && _startPageLayout.getVisibility() == View.VISIBLE)
            _startPageLayout.setVisibility(View.GONE);
    }

    /**
     * for every profile tab (3 in total), find their
     * AvatarImgView
     * NameTextView
     * ApTextView
     * HighscoreTextView
     * so they can be updated (depending on userSaves at sharedPrefs)
     * <p>
     * following only as Views as we only need their visibility attribute
     * ClockImg
     * CreateBtn
     * LoadBtn
     * DeleteBtn
     * and change their visibility according to the state 'USER_CREATED_BOOL'
     * <p>
     * if there is no save profile:
     * change Name-TextView's visibility to gone
     * change Achievement-TextView's visibility to gone
     * change Highscore-TextView's visibility to gone
     * change Load-Button's visibility to gone
     * change Delete-Buttons's visibility to gone
     * while:
     * CreateUser-Button to visible
     * Clock-ImageView to visible
     * and the Avatar-ImageView is filled with the 'empty Avatar'.png
     * <p>
     * if there's already an existing profile, do the
     * opposite of above and fill the Avatar-ImageView with the chosen AvatarPNG-file
     *
     * @param userTab
     * @param userTabSaveState
     */
    private void arrangeProfileTabs(View userTab, SharedPreferences userTabSaveState) {

        //find needed Views
        ImageView userAvatar = userTab.findViewById(R.id.start_page_avatar_img);

        TextView nameText = userTab.findViewById(R.id.vp_start_page_name);
        _userMenuAP = userTab.findViewById(R.id.vp_start_page_achievements);
        _userMenuHS = userTab.findViewById(R.id.vp_start_page_highscore);

        /* using an ArrayList to reduce amount of code */
        // ArrayList containing all views to be visible if there is a UserProfile
        ArrayList<View> createStateUser = new ArrayList<>();
        //fill it
        createStateUser.add(userTab.findViewById(R.id.start_page_clock_img));
        createStateUser.add(userTab.findViewById(R.id.vp_start_page_create_user));

        // ArrayList containing all views to be visible if there is a UserProfile
        ArrayList<View> loadStateUser = new ArrayList<>();
        //fill it
        loadStateUser.add(nameText);
        loadStateUser.add(_userMenuAP);
        loadStateUser.add(_userMenuHS);
        loadStateUser.add(userTab.findViewById(R.id.vp_start_page_load_user));
        loadStateUser.add(userTab.findViewById(R.id.vp_start_page_delete_user));

        /*check for existing sharedPrefs and load corresponding layout with a for loop (I'm proud it worked out first try <3 loops seemed scary) */
        //when there is a user stored in the Prefs
        if (userTabSaveState.getBoolean(USER_CREATED_BOOL, false)) {

            // hide CreateBtn and ClockImg
            for (int i = 0; i < createStateUser.size(); i++) {
                createStateUser.get(i).setVisibility(View.GONE);
            }
            // show rest (except AvatarImg)
            for (int i = 0; i < loadStateUser.size(); i++) {
                loadStateUser.get(i).setVisibility(View.VISIBLE);
            }

            //update View-Values using sharedPrefs
            nameText.setText(userTabSaveState.getString(USER_NAME_STRING, "") + "");
            _userMenuAP.setText(getString(R.string.txt_profile_achievement, userTabSaveState.getInt(USER_ACHIEVEMENT_POINTS_INT, 0)));
            _userMenuHS.setText(getString(R.string.txt_profile_highscore, userTabSaveState.getInt(USER_HIGHSCORE_INT, 0)));
            displayCorrectAvatar(userAvatar, userTabSaveState);

        }
        //when there is no user stored in the Prefs
        else {
            // show CreateBtn and ClockImg
            for (int i = 0; i < createStateUser.size(); i++) {
                createStateUser.get(i).setVisibility(View.VISIBLE);
            }
            // hide rest
            for (int i = 0; i < loadStateUser.size(); i++) {
                loadStateUser.get(i).setVisibility(View.GONE);
            }

            userAvatar.setImageResource(R.drawable.img_avatar_empty);
        }
    }

    /**
     * every avatar is assigned a number from 1 to 6 and a gender (male and female)
     * this method changes passed ImageViews src depending on those two values stored in the
     * sharedPrefs
     * If there are no values, returns 0 and it uses the empty_avatar_img
     * <p>
     * last time used is when the 'Load' Button is pressed and the game's avatar has been set
     *
     * @param userAvatar
     * @param avatarOwner
     */
    private void displayCorrectAvatar(ImageView userAvatar, SharedPreferences avatarOwner) {

        // get the chosenAvatar int and if it isn't 0 also check the gender and assign
        // the corresponding img
        switch (avatarOwner.getInt(USER_AVATAR_INT, 0)) {
            case 0:
                userAvatar.setImageResource(R.drawable.img_avatar_empty);
                break;
            case 1:
                if (avatarOwner.getBoolean(USER_IS_MALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_male01);
                if (avatarOwner.getBoolean(USER_IS_FEMALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_female01);
                break;
            case 2:
                if (avatarOwner.getBoolean(USER_IS_MALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_male02);
                if (avatarOwner.getBoolean(USER_IS_FEMALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_female02);
                break;
            case 3:
                if (avatarOwner.getBoolean(USER_IS_MALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_male03);
                if (avatarOwner.getBoolean(USER_IS_FEMALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_female03);
                break;
            case 4:
                if (avatarOwner.getBoolean(USER_IS_MALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_male04);
                if (avatarOwner.getBoolean(USER_IS_FEMALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_female04);
                break;
            case 5:
                if (avatarOwner.getBoolean(USER_IS_MALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_male05);
                if (avatarOwner.getBoolean(USER_IS_FEMALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_female05);
                break;
            case 6:
                if (avatarOwner.getBoolean(USER_IS_MALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_male06);
                if (avatarOwner.getBoolean(USER_IS_FEMALE_BOOL, false))
                    userAvatar.setImageResource(R.drawable.img_avatar_female06);
                break;
            default:
                userAvatar.setImageResource(R.drawable.img_avatar_empty);
                break;
        }
    }

    /* *************
    * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
    * |  onClicks        |
    * |________________*/

    /**
     * triggers the secretQuestion on the startMenuPage.
     * if clicked and an old user is loaded, the secretQuestion will start immediately on load, skipping the startPage
     * if clicked and a new user is loaded, the secretQuestion will start after the Info- and HelpPage
     * if clicked and the loaded user already answred the question, nothing happens
     *
     * @param view
     */
    public void toggleSecretQuestion(View view) {

        _secretQuestionTriggered = true;
        makeText(getApplicationContext(), R.string.secret_toast_start_page_profiles, Toast.LENGTH_SHORT).show();
    }

    /**
     * createButton on each profile
     * checks from which profiles button the request came and stores it's index
     * for reference when saving to sharedPrefs at the end of the wizard
     * <p>
     * then it inflates/displays the Wizard and manages its correct display
     *
     * @param view
     */
    public void createUser(View view) {

        //get profileNumber
        switch (((View) view.getParent()).getId()) {
            case R.id.start_page_profile_one:
                _userToCreate = 1;
                break;
            case R.id.start_page_profile_two:
                _userToCreate = 2;
                break;
            case R.id.start_page_profile_three:
                _userToCreate = 3;
                break;
            default:
                break;
        }

        //change the current page to 1
        _currentWizardPage = 1;

        //hide startPage
        displayRootStartPage(false);

        //inflate/display wizardParent
        displayUserWizardParent(true);

        //display first subPage of the Wizard
        arrangeCorrectWizardPage(_currentWizardPage);
    }

    /**
     * loadButton on each profile
     * checks from which profiles button the request came and checks corresponding sharedPrefs
     * for reference when loading the gameScreen
     * then it inflates the gameScreen and removes startPage and the whole Wizard for better performance
     * <p>
     * Question for reviewer:
     * after removing both Views, would there be a way to bring them back with my code-setup?
     * After the gameScreen there is no way to return to the startPage before restarting the app
     * so I don't really need it but if I'd like to add an "return to startPage" button in the
     * gameScreen, could I bring them back?
     * I've read about fragments, is that the only solution?
     *
     * @param view
     */
    public void loadUser(View view) {

        //load this users data
        SharedPreferences loadThis;

        //fill loadThis appropriately
        switch (((View) view.getParent()).getId()) {
            case R.id.start_page_profile_one:
                loadThis = _userOnePrefs;
                break;
            case R.id.start_page_profile_two:
                loadThis = _userTwoPrefs;
                break;
            case R.id.start_page_profile_three:
                loadThis = _userThreePrefs;
                break;
            default:
                loadThis = null;
                break;
        }

        final int loginCount = loadThis.getInt(USER_TIMES_LOGGED_IN_INT, 0) + 1;
        loadThis.edit().putInt(USER_TIMES_LOGGED_IN_INT, loginCount).apply();

        //display the ViewStub
        ViewStub gamePage = (ViewStub) findViewById(R.id.viewstub_linlay_parent_game_screen);
        gamePage.setVisibility(View.VISIBLE);

        //remove startPage and Wizard, the latter only if it has been inflated
        FrameLayout parent = (FrameLayout) findViewById(R.id.frame_layout_main);
        parent.removeView(_startPageLayout);
        if (_userWizardLayout != null)
            parent.removeView(_userWizardLayout);

        //load data
        displayLoadedData(loadThis);
    }

    /**
     * deleteButton on each profile
     * checks from which profiles button the request came and get corresponding sharedPref
     * display a DialogBox for confirmation, clears the users sharedPrefs on yes, does nothing on no
     *
     * @param view
     */
    public void deleteUser(View view) {

        /* first check which profile to delete and get its username for
        "personal confirmation" at the dialog box */
        final int profileToDelete;
        final String userNameToDelete;

        switch (((View) view.getParent()).getId()) {
            case R.id.start_page_profile_one:
                profileToDelete = 1;
                userNameToDelete = _userOnePrefs.getString(USER_NAME_STRING, "" + profileToDelete);
                break;
            case R.id.start_page_profile_two:
                profileToDelete = 2;
                userNameToDelete = _userTwoPrefs.getString(USER_NAME_STRING, "" + profileToDelete);
                break;
            case R.id.start_page_profile_three:
                profileToDelete = 3;
                userNameToDelete = _userThreePrefs.getString(USER_NAME_STRING, "" + profileToDelete);
                break;
            default:
                profileToDelete = 0;
                userNameToDelete = "" + profileToDelete;
        }

        showDialogBoxDeleteUser(profileToDelete, userNameToDelete);
    }

    /**
     * simple Pop-up DialogBox asking for delete-confirmation
     *
     * @param profileToDelete
     * @param userNameToDelete
     */
    private void showDialogBoxDeleteUser(final int profileToDelete, String userNameToDelete) {

        // displays a YES/NO Dialog Box to ask if the user really want's to delete this profile
        //instantiate
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        //Set title
        alertBuilder.setTitle(R.string.dialog_yes_no_delete_profile_title);
        //set message
        alertBuilder.setMessage(getString(R.string.dialog_yes_no_delete_profile_message, userNameToDelete));


        //yes deletes the profile and arranges the tab to display an empty profile
        //no closes the dialog box without effects
        alertBuilder.setPositiveButton(R.string.dialog_yes_no_delete_profile_yes, new DialogInterface.OnClickListener() {

            // delete profile on yes
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (profileToDelete) {
                    case 1:
                        _editUserOneSave.clear();
                        _editUserOneSave.apply();
                        arrangeProfileTabs(findViewById(R.id.start_page_profile_one), _userOnePrefs);
                        break;
                    case 2:
                        _editUserTwoSave.clear();
                        _editUserTwoSave.apply();
                        arrangeProfileTabs(findViewById(R.id.start_page_profile_two), _userTwoPrefs);
                        break;
                    case 3:
                        _editUserThreeSave.clear();
                        _editUserThreeSave.apply();
                        arrangeProfileTabs(findViewById(R.id.start_page_profile_three), _userThreePrefs);
                        break;
                }
                dialog.dismiss();
            }

        });

        // do nothing on no
        alertBuilder.setNegativeButton(R.string.dialog_yes_no_delete_profile_no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }

        });

        //create
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

/* *********************************************************************************************
  * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\
  * |                                                                                              |
  * |  createUserWizard                                                                            |
  * |                                                                                              |
  * |    only inflated if the user clicks on one of the three CreateButtons on the startPage       |
  * |____________________________________________________________________________________________*/
    /* *************
    * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
    * |  displayManager  |
    * |________________*/

    /**
     * inflates and manages visibility of the whole createUserWizard (not the subPages)
     * used a ViewStub so the Layout won't have to be loaded if the user just wants
     * to start playing with an already existing profile
     * <p>
     * inflates layout if it hasn't been already
     * if passing true changes visibility to visible, if it isn't already
     * if passing false changes visibility to gone, if it isn't already
     *
     * @param isVisible
     */
    private void displayUserWizardParent(final boolean isVisible) {

        // inflate if it hasn't been already
        if (_userWizardLayout == null) {
            ViewStub stub = (ViewStub) findViewById(R.id.viewstub_linlay_parent_create_wizard);
            _userWizardLayout = stub.inflate();
        }
        // display or hide, depending on the parameter
        else if (isVisible && _userWizardLayout.getVisibility() == View.GONE) {
            _userWizardLayout.setVisibility(View.VISIBLE);
        } else if (!isVisible && _userWizardLayout.getVisibility() == View.VISIBLE)
            _userWizardLayout.setVisibility(View.GONE);

    }

    /**
     * after passing a value from 1-3, this method takes care of displaying the subPage of
     * the createUserWizard corresponding to the value
     * <p>
     * 1 = enterNamePage
     * 2 = selectGenderPage
     * 3 = choseAvatarPage
     *
     * @param pageToDisplay
     */
    private void arrangeCorrectWizardPage(int pageToDisplay) {

        // main TextView (narrator) changing throughout pages 1-3
        TextView wizardBodyText = (TextView) findViewById(R.id.wizard_body_text);

        // ImageView displayed at the bottom for decoration, changing throughout pages 1-3
        ImageView wizardFooterImage = (ImageView) findViewById(R.id.wizard_footer_img);

        // NextButton text changes on Page 3 to finish
        /*
         * QUESTION for Reviewer:
         * I just want to change the text inside the button, would it be more efficient
         * performance-wise, to store the button in a TextView Object?
         */
        Button wizardNextButton = (Button) findViewById(R.id.vp_next_wizard_btn);

        // UserInput for the name, displayed on Page 1 only
        TextView wizardEditTextName = (TextView) findViewById(R.id.vs_create_user_name_edtxt);

        // do the magic
        switch (pageToDisplay) {
            // display first subPage
            case 1:
                // setText to ask for the users name
                wizardBodyText.setText(R.string.intro_ask_name_create_user);
                // set the footer image (deco only)
                wizardFooterImage.setImageResource(R.drawable.img_bunny_carrot);
                // make sure 'Next' is displayed on the nextButton
                wizardNextButton.setText(R.string.btn_next);

                //if the user already entered a name, clicked next and later returns to this
                //subPage, empty the editTextInput from before
                wizardEditTextName.setText("", TextView.BufferType.EDITABLE);

                //display the editText
                wizardEditTextName.setVisibility(View.VISIBLE);

                //if already initialized (thus being visible at least once already) set the spinner to gone
                if (_spinGender != null)
                    _spinGender.setVisibility(View.GONE);
                //if already initialized (thus being visible at least once already) set the gridLayout to gone
                if (_choseAvatarGrid != null)
                    _choseAvatarGrid.setVisibility(View.GONE);
                break;
            // display second subPage
            case 2:
                // setText to ask for the users gender
                wizardBodyText.setText(getString(R.string.name_mention_ask_gender_create_user, _userName));
                // set the footer image (deco only)
                wizardFooterImage.setImageResource(R.drawable.img_carrot_bunny);
                // make sure 'Next' is displayed on the nextButton
                wizardNextButton.setText(R.string.btn_next);

                //hide the editText
                wizardEditTextName.setVisibility(View.GONE);

                //if the user already selected a gender, clicked next and later returns to this
                //subPage, select the first spinnerItem again to force a new selectionProcess
                _spinGender.setSelection(0);
                //display the spinner
                _spinGender.setVisibility(View.VISIBLE);

                //if already initialized (thus being visible at least once already) set the gridLayout to gone
                if (_choseAvatarGrid != null)
                    _choseAvatarGrid.setVisibility(View.GONE);
                break;
            // display third subPage
            case 3:
                // setText to as the user to chose an avatar
                wizardBodyText.setText(R.string.choose_avatar_create_user);
                // set the footer image (deco only)
                wizardFooterImage.setImageResource(R.drawable.img_lets_start);
                // make sure 'Finish' is displayed on the nextButton
                wizardNextButton.setText(R.string.btn_next_wizard_finish);

                //display gridLayout
                _choseAvatarGrid.setVisibility(View.VISIBLE);

                //hide editText
                wizardEditTextName.setVisibility(View.GONE);

                //hide spinner
                _spinGender.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    /**
     * initializes the SelectGender Spinner and fills it with a stringArray.
     * Also sets a listener which changes the temporary gender variables accordingly
     */
    private void readySpinnerAndListener() {

        //initialize if it hasn't been already
        if (_spinGender == null)
            _spinGender = (Spinner) findViewById(R.id.wizard_gender_spinner);

        // Create an ArrayAdapter using the string array and a default _spinGender layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_genders_create_user, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the _spinGender
        _spinGender.setAdapter(adapter);

        //Listener
        //changes tempGenderBool by selecting any of its items which eventually
        //trigger the method onItemSelected.
        _spinGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //reset on first item(nothing) selected
                if (pos == 0) {
                    _isMale = false;
                    _isFemale = false;
                }
                //if male is selected
                if (pos == 1) {
                    _isMale = true;
                    _isFemale = false;

                    //display toast
                    makeText(getApplicationContext(), R.string.toast_is_male, Toast.LENGTH_SHORT).show();
                }
                //if female is selected
                if (pos == 2) {
                    _isMale = false;
                    _isFemale = true;

                    //display toast
                    makeText(getApplicationContext(), R.string.toast_is_female, Toast.LENGTH_SHORT).show();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                //such empty
            }
        });

    }

    /**
     * initializes the gridLayout holding 6 ImageButtons which are filled so each represents
     * an option to chose the avatar from and set up an onFocusListener
     */
    private void fillAvatarGridSelection() {

        //initialize if it hasn't been already
        if (_choseAvatarGrid == null)
            _choseAvatarGrid = (GridLayout) findViewById(R.id.wizard_avatar_grid_parent);

        //store the ImageButtons and assign a onFocusListener to all of them
        ImageButton avatarOne = (ImageButton) findViewById(R.id.imgbtn_select_avatar_one);
        addOnFocusListenerChooseAvatar(avatarOne, 1);
        ImageButton avatarTwo = (ImageButton) findViewById(R.id.imgbtn_select_avatar_two);
        addOnFocusListenerChooseAvatar(avatarTwo, 2);
        ImageButton avatarThree = (ImageButton) findViewById(R.id.imgbtn_select_avatar_three);
        addOnFocusListenerChooseAvatar(avatarThree, 3);
        ImageButton avatarFour = (ImageButton) findViewById(R.id.imgbtn_select_avatar_four);
        addOnFocusListenerChooseAvatar(avatarFour, 4);
        ImageButton avatarFive = (ImageButton) findViewById(R.id.imgbtn_select_avatar_five);
        addOnFocusListenerChooseAvatar(avatarFive, 5);
        ImageButton avatarSix = (ImageButton) findViewById(R.id.imgbtn_select_avatar_six);
        addOnFocusListenerChooseAvatar(avatarSix, 6);
        //fill the ImageButtons with appropriate content
        if (_isMale) {
            avatarOne.setImageResource(R.drawable.img_avatar_male01);
            avatarTwo.setImageResource(R.drawable.img_avatar_male02);
            avatarThree.setImageResource(R.drawable.img_avatar_male03);
            avatarFour.setImageResource(R.drawable.img_avatar_male04);
            avatarFive.setImageResource(R.drawable.img_avatar_male05);
            avatarSix.setImageResource(R.drawable.img_avatar_male06);
        }
        if (_isFemale) {
            avatarOne.setImageResource(R.drawable.img_avatar_female01);
            avatarTwo.setImageResource(R.drawable.img_avatar_female02);
            avatarThree.setImageResource(R.drawable.img_avatar_female03);
            avatarFour.setImageResource(R.drawable.img_avatar_female04);
            avatarFive.setImageResource(R.drawable.img_avatar_female05);
            avatarSix.setImageResource(R.drawable.img_avatar_female06);
        }

        //focus the first avatar on display
        avatarOne.requestFocus();
    }

    /**
     * change the tempChosenAvatarInt depending on which ImageButton has been focused
     *
     * @param button
     * @param number
     */
    private void addOnFocusListenerChooseAvatar(ImageButton button, final int number) {

        /*Question for Reviewer:
            why doesn't onClick work without implementing an onclick listener?*/

        //add listener
        button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                //does nothing without focus
                if (!hasFocus)
                    return;

                //changes global on each new focus
                _avatarSelected = number;
            }

        });
    }

    /* *************
    * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
    * |  onClicks        |
    * |________________*/

    /**
     * when the backButton is pressed, check the currentSubPage and pass it
     * to the wizardNavigator together with a decremented pageNumber to hide the
     * currentPage and display the decrementedCurrentPage
     *
     * @param view
     */
    public void previousPage(View view) {

        //store current page
        int from = _currentWizardPage;
        //decrement
        _currentWizardPage--;
        //store currentPage minus 1 (the goalPage)
        int to = _currentWizardPage;

        //hides from and displays to
        createUserWizardNavigator(from, to);

    }

    /**
     * when the cancelButton is pressed, check the currentSubPage and pass it
     * to the wizardNavigator together the int 0 (stand for the startPage)
     * currentPage will be hidden and page 0 will be displayed
     *
     * @param view
     */
    public void cancelWizard(View view) {
        //hide current, show rootPage
        createUserWizardNavigator(_currentWizardPage, 0);
    }

    /**
     * when the nextButton is pressed, check the currentSubPage and start the method fitting
     * to check the userInput and decide if the next subPage can be shown.
     * on the last page, when changed to finishButton, save the values, hide the Wizard and return
     * to the startScreen to show the newly created profile
     *
     * @param view
     */
    public void checkUserInput(View view) {

        switch (_currentWizardPage) {
            case 1:
                //check if input is valid and act accordingly
                checkInputUserName();
                break;
            case 2:
                //check if input is valid and act accordingly
                checkInputGender();
                break;
            //save values to the correct sharedPref instance and arrange its startPage.profileTab
            case 3:
                if (_userToCreate == 1) {
                    saveCreatedUserToPrefs(_editUserOneSave);
                    arrangeProfileTabs(findViewById(R.id.start_page_profile_one), _userOnePrefs);
                }
                if (_userToCreate == 2) {
                    saveCreatedUserToPrefs(_editUserTwoSave);
                    arrangeProfileTabs(findViewById(R.id.start_page_profile_two), _userTwoPrefs);
                }
                if (_userToCreate == 3) {
                    saveCreatedUserToPrefs(_editUserThreeSave);
                    arrangeProfileTabs(findViewById(R.id.start_page_profile_three), _userThreePrefs);
                }

                // go back to startPage
                displayRootStartPage(true);
                displayUserWizardParent(false);
                break;
            default:
                break;
        }

    }

    /**
     * when the checkUserInput method accepts the input, check the currentSubPage and pass it
     * to the wizardNavigator together with an incremented pageNumber to hide the
     * currentPage and display the incrementedCurrentPage
     */
    private void nextPage() {
        //store currentPage
        int from = _currentWizardPage;
        //increment
        _currentWizardPage++;
        //store current page plus 1 (the goalPage)
        int to = _currentWizardPage;

        //hide from, display to
        createUserWizardNavigator(from, to);
    }

    /**
     * checks users nameInput, displays an errorToast if the text doesn't
     * fit. If it does, saves the Input to the tempName variable and loads the next page.
     * Additionally, populates the spinner on the next page, if it hasn't been already
     */
    private void checkInputUserName() {

        EditText edtEnteredName = (EditText) findViewById(R.id.vs_create_user_name_edtxt);

        //min amount of characters to enter, errorTextString changes with this int, no need to change manually
        int minCharsForName = 4;

        //error if too short
        if (edtEnteredName.getText().length() < minCharsForName)
            makeText(getApplicationContext(), getString(R.string.edt_name_error, minCharsForName), Toast.LENGTH_SHORT).show();
            //store if it is ok
        else {
            _userName = edtEnteredName.getText().toString();

            //populate spinner
            if (_spinGender == null)
                readySpinnerAndListener();

            nextPage();
        }

    }

    /**
     * checks users genderInput, displays an errorToast if the selection doesn't
     * fit. If it does, saves the Input to the tempGender variable and loads the next page.
     * Additionally, populates the GridLayout on the next page, if it hasn't been already
     */
    private void checkInputGender() {

        //if a change has been made
        if (_isMale || _isFemale) {

            fillAvatarGridSelection();
            nextPage();

            //error if the first item is still select on nextClick
        } else {

            makeText(getApplicationContext(), R.string.spin_gender_error, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * after pressing the finishButton on the third subPage of the wizard, store all
     * tempPrefVariables to the corresponding sharedPrefs and rest the tempVars, in case the user
     * wants to create another one
     *
     * @param userToStore
     */
    private void saveCreatedUserToPrefs(SharedPreferences.Editor userToStore) {

        userToStore.putBoolean(USER_CREATED_BOOL, true);
        //italic with wrap_content cuts off the rightmost top-corner of the last letter, thus im adding a space as a solution
        userToStore.putString(USER_NAME_STRING, _userName + " ");
        userToStore.putBoolean(USER_IS_MALE_BOOL, _isMale);
        userToStore.putBoolean(USER_IS_FEMALE_BOOL, _isFemale);
        userToStore.putInt(USER_AVATAR_INT, _avatarSelected);
        userToStore.putInt(USER_HIGHSCORE_INT, 0);
        userToStore.putInt(USER_ACHIEVEMENT_POINTS_INT, 0);
        //prompts the user to try it again on fail
        if (!userToStore.commit())
            makeText(getApplicationContext(), R.string.create_user_error, Toast.LENGTH_LONG).show();
            // reset
        else {
            _userToCreate = 0;
            _userName = "";
            _isMale = false;
            _isFemale = false;
            _avatarSelected = 1;
        }
    }


/* *********************************************************************************************
  * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\
  * |                                                                                              |
  * |  gameScreen                                                                                  |
  * |                                                                                              |
  * |    only inflated if the user clicks on one of the three LoadButtons on the startPage         |
  * |____________________________________________________________________________________________*/
    /* *************
    * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
    * |  displayManager  |
    * |________________*/

    /**
     * loads sharedPref values and arranges gameScreen accordingly as soon as it's been loaded
     *
     * @param loadThis
     */
    private void displayLoadedData(SharedPreferences loadThis) {

        /* find views to be updated */

        // avatarImg
        ImageButton userAvatar = (ImageButton) findViewById(R.id.img_btn_game_avatar);
        // textViews for name, achievementPoints and highscore
        TextView nameText = (TextView) findViewById(R.id.vp_start_page_name);
        _userMenuAP = (TextView) findViewById(R.id.vp_start_page_achievements);
        _userMenuHS = (TextView) findViewById(R.id.vp_start_page_highscore);

        //set values to topMenuBar
        nameText.setText(loadThis.getString(USER_NAME_STRING, ""));
        _userMenuAP.setText(getString(R.string.txt_points_game, loadThis.getInt(USER_ACHIEVEMENT_POINTS_INT, 0)));
        _userMenuHS.setText(getString(R.string.txt_points_game, loadThis.getInt(USER_HIGHSCORE_INT, 0)));
        displayCorrectAvatar(userAvatar, loadThis);

        //store the sharedPrefs and Editor of the loadedUser to the currentlyPlayingUser global
        _userPlaying = loadThis;
        _editUserPlaying = _userPlaying.edit();
        _editUserPlaying.apply();

        /* Initializing views needed */

        //menu buttons
        _menuTopHelp = (Button) findViewById(btn_help_menu_top);
        _menuTopMenu = (Button) findViewById(btn_open_menu_top);
        //TitleText
        _titleText = (TextView) findViewById(R.id.game_screen_title_text);
        //BodyText
        _bodyText = (TextView) findViewById(R.id.game_screen_menu_text_main);
        //HeaderImage
        _professorImg = (ImageView) findViewById(R.id.img_header_professor_game);
        //UserInputButton on bottom
        _userInputBtn = (Button) findViewById(R.id.btn_user_input_game_screen);
        //JokerButtons
        _jokerChanceBtn = (ImageButton) findViewById(R.id.img_btn_joker_chance);
        _jokerTimeBtn = (ImageButton) findViewById(R.id.img_btn_joker_time);
        _jokerChanceBtn.setVisibility(View.INVISIBLE);
        _jokerTimeBtn.setVisibility(View.INVISIBLE);
        _relLayMenuFrameParent = findViewById(R.id.rellay_game_screen_menu_parent);

        //get achievement toast
        _achievementToast = makeText(getApplicationContext(), R.string.achievement_unlocked, Toast.LENGTH_LONG);
        _achievementToast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);

        //check secretQuestionTrigger
        if (_secretQuestionTriggered && !_userPlaying.getBoolean(USER_ANSWERED_SECRET_QUESTION_BOOL, false))
            _editUserPlaying.putBoolean(USER_TRIGGERED_SECRET_QUESTION_BOOL, true).apply();

        //check if its the first time playing for this profile
        // if it is, start at page 0 and set inMenu to avoid conflict
        // if it isn't, start on page 2
        //also check for secretQuestionTriggered and show this one first before the StartPage
        //if first time playing
        if (!_userPlaying.getBoolean(USER_HAS_PLAYED_BEFORE_BOOL, false)) {
            _currentGamePage = INFO_PAGE;
            _returnTo = START_PAGE;
            gameScreenNavigator(START_PAGE, INFO_PAGE);
        }

        //not a first time player
        else if (_userPlaying.getBoolean(USER_HAS_PLAYED_BEFORE_BOOL, false)) {
            //no secret question
            if (!_userPlaying.getBoolean(USER_TRIGGERED_SECRET_QUESTION_BOOL, false)) {
                _currentGamePage = START_PAGE;
                gameScreenNavigator(START_PAGE, START_PAGE);
            }
            //secret question triggered
            else {
                _currentGamePage = PRE_QUESTION_COUNTDOWN_PAGE;
                _returnTo = START_PAGE;
                gameScreenNavigator(_currentGamePage, PRE_QUESTION_COUNTDOWN_PAGE);
            }
        }
    }

    /**
     * manages visibility and the sharedPrefs USER_HAS_PLAYED_BEFORE_BOOL value
     * <p>
     * updated and visible on true
     * gone on false
     * USER_HAS_PLAYED_BEFORE_BOOL set to true the first time this method is started on the currentlyPlayingProfile
     *
     * @param isVisible
     */
    private void displayInfoPage(final boolean isVisible) {
        /*Question for Reviewer:

             * Does it make any kind of difference if I change the textSize in Java on runtime or in XML?
             * is it a waste to check if its visible before making it visible? or is it good practice to do so?*/

        //get the headerButton
        View headerSecretQuestion = findViewById(img_btn_secret_question);

        // update and show if needed
        //display
        if (isVisible) {

            _isInMenu = true;
            manageMenuButtons(false);
            manageJokerButtons(false);

            //swap the professor with the secretQuestionHeader
            _professorImg.setVisibility(View.GONE);
            headerSecretQuestion.setVisibility(View.VISIBLE);

            //display correct title
            _titleText.setText(R.string.title_game_info_page);
            // change the boy text
            _bodyText.setText(R.string.body_game_info_page);
            //correct text on userInputButton
            _userInputBtn.setText(R.string.btn_info_user_input);

        }
        //hide
        if (!isVisible) {
            //bring back the professor
            headerSecretQuestion.setVisibility(View.GONE);
            _professorImg.setVisibility(View.VISIBLE);
        }
    }

    /**
     * manages visibility tutorial
     * <p>
     * visible and updated on true
     * gone on false
     *
     * @param isVisible
     */
    private void displayHelpPage(final boolean isVisible) {

        //if it hasn't been already, mark that it's not the first time for the user to play
        if (!_userPlaying.getBoolean(USER_HAS_PLAYED_BEFORE_BOOL, false))
            _editUserPlaying.putBoolean(USER_HAS_PLAYED_BEFORE_BOOL, true);
        _editUserPlaying.apply();

        // update and show if needed
        if (isVisible) {

            _isInMenu = true;
            manageMenuButtons(false);
            manageJokerButtons(false);

            findViewById(R.id.scroll_tutorial).setVisibility(View.VISIBLE);
            //display correct title
            _titleText.setText(R.string.title_game_help_page);
            // let the professor talk
            _professorImg.setImageResource(R.drawable.img_menu_mark);
            // change the boy text
            _bodyText.setText("");
            //correct text on userInputButton
            _userInputBtn.setText(R.string.btn_help_user_input);

        } else {
            _isInMenu = false;
            findViewById(R.id.scroll_tutorial).setVisibility(View.GONE);
        }
        return;
    }

    /**
     * manages visibility of the startPage
     * <p>
     * visible and updated on true
     * gone on false
     *
     * @param isVisible
     */
    private void displayGameStartPage(final boolean isVisible) {

        // update and show if needed
        if (isVisible) {
            _isInMenu = false;
            manageMenuButtons(true);
            manageJokerButtons(false);

            //hide title
            _titleText.setText("");
            // let the professor talk
            _professorImg.setImageResource(R.drawable.img_talking_mark);
            // change the boy text
            _bodyText.setText(R.string.body_game_start_page);
            //correct text on userInputButton
            _userInputBtn.setText(R.string.btn_start_user_input);

        } else {
        }
    }

    /**
     * manages visibility of the cheatQuestion
     * <p>
     * visible on true
     * gone on false
     *
     * @param isVisible
     */
    private void displayCheatQuestion(final boolean isVisible){

        View questionText = findViewById(R.id.cheat_code_text_question);
        View answerEditText = findViewById(R.id.cheat_code_text_answer);

        // update and show if needed
        if (isVisible) {
            _isInMenu = true;
            manageMenuButtons(false);
            manageJokerButtons(false);

            //display elements
            questionText.setVisibility(View.VISIBLE);
            answerEditText.setVisibility(View.VISIBLE);
            //hide title
            _titleText.setText("");
            // let the professor talk
            _professorImg.setImageResource(R.drawable.img_menu_mark);
            // change the boy text
            _bodyText.setVisibility(View.GONE);
            //correct text on userInputButton
            _userInputBtn.setText(R.string.btn_submit_answer);

        } else {
            //hide elements
            questionText.setVisibility(View.GONE);
            answerEditText.setVisibility(View.GONE);
            // change the boy text
            _bodyText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * manages visibility of the categorySelectionPage
     * <p>
     * visible and updated on true
     * gone on false
     *
     * @param isVisible
     */
    private void displayCategorySelectionPage(final boolean isVisible) {

        //get the _categoryButtons if they haven't been already
        if (_categoryButtons == null) {

            _categoryButtons = new ArrayList<>();
            _categoryButtons.add(findViewById(img_btn_category_a_game));
            _categoryButtons.add(findViewById(img_btn_category_b_game));
            _categoryButtons.add(findViewById(img_btn_category_n_game));
            _categoryButtons.add(findViewById(img_btn_category_d_game));

        }
        /* set and remove listener */
        // Question for Reviewer:
        //  I didn't have any problems without removing the listeners, but except for safety, does it increase performance as well or are they deactivated anyway when visibility is set to "gone"?
        //  add listener
        if (isVisible) {
            _categoryButtons.get(0).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    //if there arent any questions left, disable the category
                    if (_disableAnimalsEasyScore && _disableAnimalsHardScore) {
                        makeText(getApplicationContext(), R.string.no_questions_left_toast, Toast.LENGTH_LONG).show();
                    } else {
                        _categoryChosen = ANIMALS_CATEGORY;
                        _titleText.setText(R.string.title_game_category_a_selected);
                        _userInputBtn.setVisibility(View.VISIBLE);
                    }

                }

            });

            _categoryButtons.get(1).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    //if there arent any questions left, disable the category
                    if (_disableBooksEasyScore && _disableBooksHardScore) {
                        makeText(getApplicationContext(), R.string.no_questions_left_toast, Toast.LENGTH_LONG).show();
                    } else {
                        _categoryChosen = BOOKS_CATEGORY;
                        _titleText.setText(R.string.title_game_category_b_selected);
                        _userInputBtn.setVisibility(View.VISIBLE);
                    }

                }

            });

            _categoryButtons.get(2).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    //if there arent any questions left, disable the category
                    if (_disableNetflixEasyScore && _disableNetflixHardScore) {
                        makeText(getApplicationContext(), R.string.no_questions_left_toast, Toast.LENGTH_LONG).show();
                    } else {
                        _categoryChosen = NETFLIX_CATEGORY;
                        _titleText.setText(R.string.title_game_category_n_selected);
                        _userInputBtn.setVisibility(View.VISIBLE);
                    }

                }

            });

            _categoryButtons.get(3).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    //if there arent any questions left, disable the category
                    if (_disableDigitalEasyScore && _disableDigitalHardScore) {
                        makeText(getApplicationContext(), R.string.no_questions_left_toast, Toast.LENGTH_LONG).show();
                    } else {
                        _categoryChosen = DIGITAL_CATEGORY;
                        _titleText.setText(R.string.title_game_category_d_selected);
                        _userInputBtn.setVisibility(View.VISIBLE);
                    }

                }

            });
            //remove listener
        } else {
            for (int i = 0; i < _categoryButtons.size(); i++) {
                _categoryButtons.get(i).setOnFocusChangeListener(null);
            }
        }

        // show if needed
        if (isVisible) {

            _isInMenu = false;
            manageMenuButtons(true);
            manageJokerButtons(false);

            //make sure no button is being focused
            _professorImg.requestFocus();

            if (_disableAnimalsEasyScore && _disableAnimalsHardScore)
                _categoryButtons.get(0).setAlpha(0.1f);
            else
                _categoryButtons.get(0).setAlpha(1f);

            if (_disableBooksEasyScore && _disableBooksHardScore)
                _categoryButtons.get(1).setAlpha(0.1f);
            else
                _categoryButtons.get(1).setAlpha(1f);

            if (_disableNetflixEasyScore && _disableNetflixHardScore)
                _categoryButtons.get(2).setAlpha(0.1f);
            else
                _categoryButtons.get(2).setAlpha(1f);
            ;

            if (_disableDigitalEasyScore && _disableDigitalHardScore)
                _categoryButtons.get(3).setAlpha(0.1f);
            else
                _categoryButtons.get(3).setAlpha(1f);

            //hide userInputButton and set text for later
            _userInputBtn.setVisibility(View.INVISIBLE);
            _userInputBtn.setText(R.string.btn_next);
            //hide bodyText
            _bodyText.setText("");

            //display category buttons
            for (int i = 0; i < _categoryButtons.size(); i++) {
                _categoryButtons.get(i).setVisibility(View.VISIBLE);
            }

            // let the professor talk
            _professorImg.setImageResource(R.drawable.img_talking_mark);
            // title text
            _titleText.setText(R.string.title_game_category_selection);
            //hide userInputButton
            _userInputBtn.setVisibility(View.INVISIBLE);

        }

        if (!isVisible) {
            //hide elements and show userButton again
            _userInputBtn.setVisibility(View.VISIBLE);
            //hide buttons
            for (int i = 0; i < _categoryButtons.size(); i++) {
                _categoryButtons.get(i).setVisibility(View.GONE);
            }
        }
    }

    /**
     * manages visibility of the scoreValueChoicePage
     * <p>
     * visible and updated on true
     * gone on false
     *
     * @param isVisible
     */
    private void displayScoreValueChoicePage(final boolean isVisible) {

        // get the scoreButtons
        final Button lowScoreButton = (Button) findViewById(btn_sore_value_low_game_screen);
        final Button highScoreButton = (Button) findViewById(btn_sore_value_high_game_screen);

        if (isVisible) {

            _isInMenu = false;
            manageMenuButtons(true);
            manageJokerButtons(false);

            //focus the prof for attention and to let the user do the first move
            _professorImg.requestFocus();

            //display buttons
            lowScoreButton.setVisibility(View.VISIBLE);
            highScoreButton.setVisibility(View.VISIBLE);


            //update elements
            _userInputBtn.setText(R.string.btn_back);
            _bodyText.setText("");
            _professorImg.setImageResource(R.drawable.img_talking_mark);
            _titleText.setText(R.string.title_game_value_selection_default);

            //grey out and disable if no questions left
            if (_categoryChosen == ANIMALS_CATEGORY && _disableAnimalsEasyScore || _categoryChosen == BOOKS_CATEGORY && _disableBooksEasyScore || _categoryChosen == NETFLIX_CATEGORY && _disableNetflixEasyScore || _categoryChosen == DIGITAL_CATEGORY && _disableDigitalEasyScore) {
                lowScoreButton.setAlpha(0.1f);
                lowScoreButton.setClickable(false);
            } else {
                lowScoreButton.setAlpha(1f);
                lowScoreButton.setClickable(true);
            }
            if (_categoryChosen == ANIMALS_CATEGORY && _disableAnimalsHardScore || _categoryChosen == BOOKS_CATEGORY && _disableBooksHardScore || _categoryChosen == NETFLIX_CATEGORY && _disableNetflixHardScore || _categoryChosen == DIGITAL_CATEGORY && _disableDigitalHardScore) {
                highScoreButton.setAlpha(0.1f);
                highScoreButton.setClickable(false);
            } else {
                highScoreButton.setAlpha(1f);
                highScoreButton.setClickable(true);
            }

            lowScoreButton.setText(R.string.btn_score_low_game);
            highScoreButton.setText(R.string.btn_score_high_game);

            //Set focus listener

            lowScoreButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;
                    if (_categoryChosen == ANIMALS_CATEGORY && !_disableAnimalsEasyScore ||
                            _categoryChosen == BOOKS_CATEGORY && !_disableBooksEasyScore ||
                            _categoryChosen == NETFLIX_CATEGORY && !_disableNetflixEasyScore ||
                            _categoryChosen == DIGITAL_CATEGORY && !_disableDigitalEasyScore) {

                        _questionDifficultyChosen = EASY_DIFFICULTY;
                        _scoreToAdd = 150;
                        _titleText.setText(R.string.title_game_easy_chosen);
                        lowScoreButton.setText(R.string.btn_begin_game);
                        highScoreButton.setText(R.string.btn_score_high_game);
                    }
                }
            });


            highScoreButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    if (_categoryChosen == ANIMALS_CATEGORY && !_disableAnimalsHardScore ||
                            _categoryChosen == BOOKS_CATEGORY && !_disableBooksHardScore ||
                            _categoryChosen == NETFLIX_CATEGORY && !_disableNetflixHardScore ||
                            _categoryChosen == DIGITAL_CATEGORY && !_disableDigitalHardScore) {

                        _questionDifficultyChosen = HARD_DIFFICULTY;
                        _scoreToAdd = 300;
                        _titleText.setText(R.string.title_game_hard_chosen);
                        highScoreButton.setText(R.string.btn_begin_game);
                        lowScoreButton.setText(R.string.btn_score_low_game);
                    }

                }

            });


        }
        //hide
        if (!isVisible) {
            lowScoreButton.setOnFocusChangeListener(null);
            highScoreButton.setOnFocusChangeListener(null);
            lowScoreButton.setVisibility(View.GONE);
            highScoreButton.setVisibility(View.GONE);
        }

    }

    /**
     * manages visibility of the countdownPage before the question starts
     * <p>
     * starts a CountDownTimer which loads the elements needed for the questions to come and
     * loads the question on finish
     * <p>
     * visible and updated on true
     * gone on false
     *
     * @param isVisible
     */
    private void displayQuestionCountdownPage(final boolean isVisible) {

        //get elements
        _shortCountdownText = (TextView) findViewById(R.id.game_screen_short_countdown_text);
        _shortCountdownBackgroundImage = findViewById(R.id.game_screen_short_countdown_background_image);

        if (isVisible) {

            manageMenuButtons(false);
            manageJokerButtons(false);

            //update elements
            _titleText.setText("");
            _professorImg.setImageResource(R.drawable.img_wrong_mark_prof);
            _professorImg.setBackgroundResource(R.drawable.anim_drawable_prof_on_fire);
            _burningPassionAnimDraw = (AnimationDrawable) _professorImg.getBackground();
            _shortCountdownBackgroundImage.setVisibility(View.VISIBLE);
            _burningPassionAnimDraw.start();
            _userInputBtn.setVisibility(View.INVISIBLE);
            _bodyText.setText("");

            //create CountDownTimer, starting a question after finishing and loading views on first display, using the wait-time
            CountDownTimer timerShortBeforeQuestion = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millSecondsLeftToFinish) {
                    //load if needed
                    if (_newGame) {
                        prepareQuestionPage();
                    }

                    //display countdown on the page
                    String timer = String.valueOf(millSecondsLeftToFinish / 1000 - 1);//-1 because I want it to display 0 at the end
                    _shortCountdownText.setText(getString(R.string.seconds_till_start, timer));
                }

                //load the next screen
                @Override
                public void onFinish() {
                    //start countdown
                    _answerRadioButtonOne.setClickable(true);
                    _answerRadioButtonTwo.setClickable(true);
                    _answerRadioButtonThree.setClickable(true);
                    _answerRadioButtonFour.setClickable(true);
                    //trigger the secret question if necessary
                    if (_userPlaying.getBoolean(USER_TRIGGERED_SECRET_QUESTION_BOOL, false)) {
                        _editUserPlaying.putBoolean(USER_TRIGGERED_SECRET_QUESTION_BOOL, false).apply();
                        _jokerDoubleTimeUsed = false;
                        gameScreenNavigator(PRE_QUESTION_COUNTDOWN_PAGE, SECRET_QUESTION_PAGE);
                        //load and display question
                    } else {
                        manageQuestionCatalogue();
                        gameScreenNavigator(PRE_QUESTION_COUNTDOWN_PAGE, QUESTIONS_ASKED_PAGE);
                    }
                }
            };
            //start the timer
            timerShortBeforeQuestion.start();
        }
        //hide
        if (!isVisible) {
            _shortCountdownText.setText("");
            _shortCountdownBackgroundImage.setVisibility(View.GONE);
            _professorImg.setBackgroundResource(0);
            _userInputBtn.setVisibility(View.VISIBLE);
        }

    }


    /**
     * manages visibility of the page where the action is going on
     * <p>
     * visible on true
     * hidden on false
     *
     * @param isVisible
     */
    private void displayQuestionAskedPage(final boolean isVisible) {

        if (isVisible) {

            setRadioButtonListener();

            if (!_isBonusQuestion)
                manageJokerButtons(true);
            else if (_isBonusQuestion)
                manageJokerButtons(false);

            manageMenuButtons(false);

            //set all elements
            _professorImg.setImageResource(R.drawable.img_question_mark);
            _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSecondaryDark));
            _titleText.setVisibility(View.GONE);
            _relLayMenuFrameParent.setVisibility(View.GONE);
            _linLayTimeIndicator.setVisibility(View.VISIBLE);
            _currentUserPointsText.setVisibility(View.VISIBLE);
            _questionText.setVisibility(View.VISIBLE);
            _answerRadioButtonThree.setVisibility(View.VISIBLE);
            _answerRadioButtonFour.setVisibility(View.VISIBLE);
            _userInputBtn.setText(R.string.btn_submit_answer);
            _userInputBtn.setVisibility(View.INVISIBLE);

            //reset the progressBar
            _timeLeftToAnswerBar.setProgress(100);
            _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#7CB342"), PorterDuff.Mode.SRC_IN);
            _timeLeftToAnswerBar.setBackgroundResource(R.drawable.style_score_gamescreen_secondary);
            _relLayRadioButtonParent.setVisibility(View.VISIBLE);

            /* restyle the questionText, space and radioButtons for asking a question (second design being asking the user, if he wants to try a bonus-question) */
            //QuestionText
            LinearLayout.LayoutParams paramsText = (LinearLayout.LayoutParams)
                    _questionText.getLayoutParams();
            paramsText.weight = 1.0f;
            _questionText.setLayoutParams(paramsText);

            //parent of the radioButtons
            LinearLayout.LayoutParams paramsButtons = (LinearLayout.LayoutParams)
                    _relLayRadioButtonParent.getLayoutParams();
            paramsButtons.weight = 1.2f;
            _relLayRadioButtonParent.setLayoutParams(paramsButtons);

            //space used for aligning the buttons
            View spaceAligner = findViewById(space_radio_buttons);
            RelativeLayout.LayoutParams paramsSpaceAlignButtons = (RelativeLayout.LayoutParams)
                    spaceAligner.getLayoutParams();
            paramsSpaceAlignButtons.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsSpaceAlignButtons.removeRule(RelativeLayout.CENTER_HORIZONTAL);
            paramsSpaceAlignButtons.addRule(RelativeLayout.CENTER_IN_PARENT);
            spaceAligner.setLayoutParams(paramsSpaceAlignButtons);

            //starts counting down the time left to answer, resulting in a timeOut and gameOver on finish. Interrupted by correct answers, called twice in a question if the timeJoker has been used
            startCountDownForQuestion();
        }
        //hide
        if (!isVisible) {
            _titleText.setVisibility(View.VISIBLE);
            _relLayMenuFrameParent.setVisibility(View.VISIBLE);
            _linLayTimeIndicator.setVisibility(View.GONE);
            _currentUserPointsText.setVisibility(View.GONE);
            _questionText.setVisibility(View.GONE);
            _relLayRadioButtonParent.setVisibility(View.GONE);
            _answerRadioButtonOne.setChecked(false);
            _answerRadioButtonTwo.setChecked(false);
            _answerRadioButtonThree.setChecked(false);
            _answerRadioButtonFour.setChecked(false);
            _answerRadioButtonOne.setOnFocusChangeListener(null);
            _answerRadioButtonTwo.setOnFocusChangeListener(null);
            _answerRadioButtonThree.setOnFocusChangeListener(null);
            _answerRadioButtonFour.setOnFocusChangeListener(null);
            _answerRadioButtonOne.setBackgroundResource(R.drawable.style_game_content_border);
            _answerRadioButtonTwo.setBackgroundResource(R.drawable.style_game_content_border);
            _answerRadioButtonThree.setBackgroundResource(R.drawable.style_game_content_border);
            _answerRadioButtonFour.setBackgroundResource(R.drawable.style_game_content_border);
            _answerRadioButtonOne.setAlpha(1f);
            _answerRadioButtonTwo.setAlpha(1f);
            _answerRadioButtonThree.setAlpha(1f);
            _answerRadioButtonFour.setAlpha(1f);
            _hiddenByChanceJoker01.setBackgroundResource(R.drawable.style_game_content_border);
            _hiddenByChanceJoker02.setAlpha(1f);
            _hiddenByChanceJoker01.setClickable(true);
            _hiddenByChanceJoker02.setClickable(true);
            _userInputBtn.setVisibility(View.VISIBLE);
            _jokerDoubleTimeUsed = false;
        }
    }

    /**
     * manages visibility of the BonusQuestionConfirmation Page
     * <p>
     * visible on true
     * hidden on false
     *
     * @param isVisible
     */
    private void displayBonusQuestionConfirmationPage(final boolean isVisible) {

        if (isVisible) {

            manageMenuButtons(false);
            manageJokerButtons(false);

            //set all elements
            _professorImg.setImageResource(R.drawable.img_question_mark);
            _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSecondaryDark));
            _titleText.setVisibility(View.GONE);
            _relLayMenuFrameParent.setVisibility(View.GONE);
            _linLayTimeIndicator.setVisibility(View.VISIBLE);
            _currentUserPointsText.setVisibility(View.VISIBLE);
            _questionText.setVisibility(View.VISIBLE);
            _timeLeftToAnswerText.setText("");

            _userInputBtn.setText(R.string.btn_submit_answer);
            _userInputBtn.setVisibility(View.INVISIBLE);

            //reset the progressBar
            _timeLeftToAnswerBar.setProgress(100);
            _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#7CB342"), PorterDuff.Mode.SRC_IN);
            _timeLeftToAnswerBar.setBackgroundResource(R.drawable.style_score_gamescreen_secondary);
            _relLayRadioButtonParent.setVisibility(View.VISIBLE);

            int bonusQuestionsLeft = _bonusBook.size();
            _questionText.setText(getString(R.string.want_start_bonus_question, bonusQuestionsLeft));
            _answerRadioButtonThree.setVisibility(View.GONE);
            _answerRadioButtonFour.setVisibility(View.GONE);
            _answerRadioButtonOne.setText(R.string.dialog_yes_no_delete_profile_yes);
            _answerRadioButtonTwo.setText(R.string.dialog_yes_no_delete_profile_no);
            _answerRadioButtonOne.setClickable(true);
            _answerRadioButtonTwo.setClickable(true);

            /* restyle the questionText, space and radioButtons for asking about the bonus question (second design being default ask-question design) */
            //QuestionText
            LinearLayout.LayoutParams paramsText = (LinearLayout.LayoutParams)
                    _questionText.getLayoutParams();
            paramsText.weight = 1.5f;
            _questionText.setLayoutParams(paramsText);

            //parent of the radioButtons
            LinearLayout.LayoutParams paramsButtons = (LinearLayout.LayoutParams)
                    _relLayRadioButtonParent.getLayoutParams();
            paramsButtons.weight = 0.7f;
            _relLayRadioButtonParent.setLayoutParams(paramsButtons);

            //space used for aligning the buttons
            View spaceAligner = findViewById(space_radio_buttons);
            RelativeLayout.LayoutParams paramsSpaceAlignButtons = (RelativeLayout.LayoutParams)
                    spaceAligner.getLayoutParams();
            paramsSpaceAlignButtons.removeRule(RelativeLayout.CENTER_IN_PARENT);
            paramsSpaceAlignButtons.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            paramsSpaceAlignButtons.addRule(RelativeLayout.CENTER_HORIZONTAL);
            spaceAligner.setLayoutParams(paramsSpaceAlignButtons);
        }

        if (!isVisible) {
            _titleText.setVisibility(View.VISIBLE);
            _relLayMenuFrameParent.setVisibility(View.VISIBLE);
            _linLayTimeIndicator.setVisibility(View.GONE);
            _currentUserPointsText.setVisibility(View.GONE);
            _questionText.setVisibility(View.GONE);
            _relLayRadioButtonParent.setVisibility(View.GONE);
            _answerRadioButtonOne.setChecked(false);
            _answerRadioButtonTwo.setChecked(false);
        }
    }

    /**
     * manages visibility of the SecretQuestion Page
     * <p>
     * visible on true
     * hidden on false
     *
     * @param isVisible
     */
    private void displaySecretQuestionPage(final boolean isVisible) {

        //display
        if (isVisible) {

            manageMenuButtons(false);
            manageJokerButtons(false);
            //set all elements
            _professorImg.setImageResource(R.drawable.img_question_mark);
            _titleText.setVisibility(View.GONE);
            _relLayMenuFrameParent.setVisibility(View.GONE);
            _questionText.setVisibility(View.VISIBLE);
            _relLayRadioButtonParent.setVisibility(View.GONE);
            _relLayCheckBoxParent.setVisibility(View.VISIBLE);
            _userInputBtn.setText(R.string.btn_submit_answer);

            //get the string array, split it and store 0 to the questionText, 1 & 2 as the correct answers and 3 & 4 as wrong ones
            String[] secretQuestion = getResources().getStringArray(R.array.question_secret);
            _questionText.setText(secretQuestion[0]);

            //fill checkboxes
            _answerCheckBoxOne.setText(secretQuestion[2]);
            _answerCheckBoxTwo.setText(secretQuestion[3]);
            _answerCheckBoxThree.setText(secretQuestion[1]);
            _answerCheckBoxFour.setText(secretQuestion[4]);

        }
        //hide
        if (!isVisible) {

            _editUserPlaying.putBoolean(USER_TRIGGERED_SECRET_QUESTION_BOOL, false).apply();
            _titleText.setVisibility(View.VISIBLE);
            _relLayMenuFrameParent.setVisibility(View.VISIBLE);
            _questionText.setVisibility(View.GONE);
            _relLayCheckBoxParent.setVisibility(View.GONE);

            _answerCheckBoxOne.setChecked(false);
            _answerCheckBoxTwo.setChecked(false);
            _answerCheckBoxThree.setChecked(false);
            _answerCheckBoxFour.setChecked(false);
        }
    }

     /* *************
    * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
    * |  Logic           |
    * |________________*/

    /**
     * enable or disable the MenuButtons
     *
     * @param isEnabled
     */
    private void manageMenuButtons(boolean isEnabled) {

        if (isEnabled) {
            _menuTopHelp.setAlpha(1f);
            _menuTopHelp.setClickable(true);
            _menuTopMenu.setAlpha(1f);
            _menuTopMenu.setClickable(true);

        } else if (!isEnabled) {
            _menuTopHelp.setAlpha(0.3f);
            _menuTopHelp.setClickable(false);
            _menuTopMenu.setAlpha(0.3f);
            _menuTopMenu.setClickable(false);
        }
    }

    /**
     * enable or disable the JokerButtons
     *
     * @param isEnabled
     */
    private void manageJokerButtons(boolean isEnabled) {

        if (isEnabled) {
            _jokerChanceBtn.setVisibility(View.VISIBLE);
            _jokerChanceBtn.setClickable(true);
            _jokerChanceBtn.setAlpha(1f);
            _jokerTimeBtn.setVisibility(View.VISIBLE);
            _jokerTimeBtn.setAlpha(1f);
            _jokerTimeBtn.setClickable(true);

        } else if (!isEnabled) {
            // Question for Reviewer
            //  is there a difference between setting alpha to zero or visibility to invisible?
            _jokerChanceBtn.setVisibility(View.INVISIBLE);
            _jokerChanceBtn.setClickable(false);
            _jokerTimeBtn.setVisibility(View.INVISIBLE);
            _jokerTimeBtn.setClickable(false);
        }
    }

    /**
     * set OnFocusListener for the Answer-RadioButtons
     */
    private void setRadioButtonListener() {
        _answerRadioButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (_currentGamePage == BONUS_QUESTION_CONFIRMATION_PAGE) {

                    _isBonusQuestion = true;
                    _answerRadioButtonOne.setChecked(true);
                    _answerRadioButtonTwo.setChecked(false);
                } else {
                    _answerRadioButtonOne.setChecked(true);
                    _answerRadioButtonTwo.setChecked(false);
                    _answerRadioButtonThree.setChecked(false);
                    _answerRadioButtonFour.setChecked(false);
                    _selectedAnswerButton = v;
                }
                _userInputBtn.setVisibility(View.VISIBLE);
            }
        });

        _answerRadioButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (_currentGamePage == BONUS_QUESTION_CONFIRMATION_PAGE) {

                    _isBonusQuestion = false;
                    _answerRadioButtonOne.setChecked(false);
                    _answerRadioButtonTwo.setChecked(true);
                } else {
                    _answerRadioButtonOne.setChecked(false);
                    _answerRadioButtonTwo.setChecked(true);
                    _answerRadioButtonThree.setChecked(false);
                    _answerRadioButtonFour.setChecked(false);
                    _selectedAnswerButton = v;
                }
                _userInputBtn.setVisibility(View.VISIBLE);
            }
        });

        _answerRadioButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _answerRadioButtonOne.setChecked(false);
                _answerRadioButtonTwo.setChecked(false);
                _answerRadioButtonThree.setChecked(true);
                _answerRadioButtonFour.setChecked(false);

                _selectedAnswerButton = v;
                _userInputBtn.setVisibility(View.VISIBLE);
            }
        });

        _answerRadioButtonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _answerRadioButtonOne.setChecked(false);
                _answerRadioButtonTwo.setChecked(false);
                _answerRadioButtonThree.setChecked(false);
                _answerRadioButtonFour.setChecked(true);

                _selectedAnswerButton = v;
                _userInputBtn.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * preLoading views needed for the questions while the short countdown runs
     */
    private void prepareQuestionPage() {
        if (_linLayTimeIndicator == null || _relLayRadioButtonParent == null || _relLayCheckBoxParent == null) {
            _linLayTimeIndicator = findViewById(R.id.question_screen_time_left_parent);
            _relLayRadioButtonParent = findViewById(R.id.game_parent_radio_buttons);
            _relLayCheckBoxParent = findViewById(R.id.game_parent_check_boxes);

            _timeLeftToAnswerText = (TextView) findViewById(R.id.game_time_left_to_answer);
            _timeLeftToAnswerBar = (ProgressBar) findViewById(R.id.game_time_progress_bar);
            _currentUserPointsText = (TextView) findViewById(R.id.game_current_points_text);
            _questionText = (TextView) findViewById(R.id.game_question_field_text);

            //question radio buttons
            _answerRadioButtonOne = (RadioButton) findViewById(R.id.radio_button_one);
            _answerRadioButtonTwo = (RadioButton) findViewById(R.id.radio_button_two);
            _answerRadioButtonThree = (RadioButton) findViewById(R.id.radio_button_three);
            _answerRadioButtonFour = (RadioButton) findViewById(R.id.radio_button_four);
            //question checkBoxes
            _answerCheckBoxOne = (CheckBox) findViewById(R.id.check_box_one);
            _answerCheckBoxTwo = (CheckBox) findViewById(R.id.check_box_two);
            _answerCheckBoxThree = (CheckBox) findViewById(R.id.check_box_three);
            _answerCheckBoxFour = (CheckBox) findViewById(R.id.check_box_four);
        }

        //reload the questionCatalogues on every new game / after every gameOver
        if (_newGame) {

            _animalsEasyBook = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.questions_animals_easy))); // to load while countdown if its a new game, all catalogues
            _animalsHardBook = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.questions_animals_hard)));
            _booksEasyBook = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.question_books_easy)));
            _booksHardBook = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.question_books_hard)));
            _netflixEasyBook = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.question_netflix_easy)));
            _netflixHardBook = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.question_netflix_hard)));
            _digitalEasyBook = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.question_digital_world_easy)));
            _digitalHardBook = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.question_digital_world_hard)));
            _bonusBook = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.question_bonus)));

            _currentUserPointsText.setText(String.format("%05d", 0));

            _newGame = false;
        }
    }

    /**
     * CountDown starting after every question is being asked limiting the time for the user to answer
     */
    private void startCountDownForQuestion() {
        //set up the timer, 2 seconds longer than displayed - accounting for loading time
        _countDownTimerQuestion = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millSecondsLeftToFinish) {
                int timeLeft = (int) millSecondsLeftToFinish / 1000; //seconds till onFinish is called
                String timerText = String.valueOf(timeLeft - 1); //so it stops at 0 and not 1

                //if the joker hasn't been activated, store the current timeLeft in case it's used and the time needs to be referenced
                if (!_jokerDoubleTimeUsed && !_isSecondCountdown)
                    _timeLeftBeforeFreezeJoker = timeLeft;

                //freeze the progressBar optically if the joker is pressed for as long as the timer finishes and the second timer arrives at the time-destination the joker was used
                if (_jokerDoubleTimeUsed && !_isSecondCountdown && _timeLeftBeforeFreezeJoker >= timeLeft) {
                    _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                    _timeLeftToAnswerBar.setBackgroundResource(R.drawable.style_button_regular);
                    _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#82b1ff"), PorterDuff.Mode.SRC_IN);
                    _professorImg.setImageResource(R.drawable.img_cold_mark);
                    _professorImg.setBackgroundResource(0);
                }

                //normal behaviour till the joker is used
                if (!_jokerDoubleTimeUsed && !_isSecondCountdown) {
                    //I set the progress manually, cause i didn't like how it looked otherwise. I didn't use animation because I liked the look
                    //reset stats to normal, everything is here so the timeJoker doesn't make any problems
                    if (timeLeft >= 11) {
                        _professorImg.setImageResource(R.drawable.img_question_mark);
                        _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSecondaryDark));
                        _timeLeftToAnswerBar.setProgress(100);
                        _timeLeftToAnswerText.setText("10");
                        _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#7CB342"), PorterDuff.Mode.SRC_IN); //is there a simpler way to change a PBar tint without additional xml or hardcoded color??
                        _timeLeftToAnswerBar.setBackgroundResource(R.drawable.style_score_gamescreen_secondary);
                    } else
                        //update text next to bar
                        _timeLeftToAnswerText.setText(timerText);

                    //change to secondary color
                    if (timeLeft >= 4 && timeLeft <= 6) {
                        _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorTextTertiaryDark));
                        _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#FBC02D"), PorterDuff.Mode.SRC_IN);
                    }
                    //change to signal color
                    if (timeLeft <= 3) {
                        _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSignalDark));
                        _timeLeftToAnswerBar.setBackgroundResource(R.drawable.style_score_gamescreen_signal);
                        _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#D32F2F"), PorterDuff.Mode.SRC_IN);
                        _professorImg.setImageResource(R.drawable.img_wrong_mark_prof);
                        _professorImg.setBackgroundResource(R.drawable.anim_drawable_prof_on_fire);
                        _burningPassionAnimDraw = (AnimationDrawable) _professorImg.getBackground();
                        _burningPassionAnimDraw.start();
                    }

                    //adjust progressBar
                    if (timeLeft == 10)
                        _timeLeftToAnswerBar.setProgress(90);
                    if (timeLeft == 9)
                        _timeLeftToAnswerBar.setProgress(80);
                    if (timeLeft == 8)
                        _timeLeftToAnswerBar.setProgress(70);
                    if (timeLeft == 7)
                        _timeLeftToAnswerBar.setProgress(60);
                    if (timeLeft == 6)
                        _timeLeftToAnswerBar.setProgress(50);
                    if (timeLeft == 5)
                        _timeLeftToAnswerBar.setProgress(40);
                    if (timeLeft == 4)
                        _timeLeftToAnswerBar.setProgress(30);
                    if (timeLeft == 3)
                        _timeLeftToAnswerBar.setProgress(20);
                    if (timeLeft == 2)
                        _timeLeftToAnswerBar.setProgress(10);
                    if (timeLeft == 1)
                        _timeLeftToAnswerBar.setProgress(0);
                }

                //secondRun variation, called after the first countdown finishes, if the timeJoker has been used
                if (_isSecondCountdown) {
                    //keep frozen till its back to its previous TimeValue
                    if (timeLeft >= _timeLeftBeforeFreezeJoker) {
                        _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                        _timeLeftToAnswerBar.setBackgroundResource(R.drawable.style_button_regular);
                        _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#82b1ff"), PorterDuff.Mode.SRC_IN);
                        _professorImg.setImageResource(R.drawable.img_cold_mark);
                        _professorImg.setBackgroundResource(0);
                    }
                    //continue like usual
                    else {
                        if (timeLeft >= 11) {
                            _professorImg.setImageResource(R.drawable.img_question_mark);
                            _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSecondaryDark));
                            _timeLeftToAnswerBar.setProgress(100);
                            _timeLeftToAnswerText.setText("10");
                            _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#7CB342"), PorterDuff.Mode.SRC_IN); //is there a simpler way to change a PBar tint without additional xml or hardcoded color??
                        } else
                            //update text next to bar
                            _timeLeftToAnswerText.setText(timerText);

                        //change to secondary color
                        if (timeLeft >= 4 && timeLeft <= 6) {
                            _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorTextTertiaryDark));
                            _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#FBC02D"), PorterDuff.Mode.SRC_IN);
                        }
                        //change to signal color
                        if (timeLeft <= 3) {
                            _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSignalDark));
                            _timeLeftToAnswerBar.setBackgroundResource(R.drawable.style_score_gamescreen_signal);
                            _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#D32F2F"), PorterDuff.Mode.SRC_IN);
                            _professorImg.setImageResource(R.drawable.img_wrong_mark_prof);
                            _professorImg.setBackgroundResource(R.drawable.anim_drawable_prof_on_fire);
                            _burningPassionAnimDraw = (AnimationDrawable) _professorImg.getBackground();
                            _burningPassionAnimDraw.start();
                        } else {
                            _timeLeftToAnswerBar.setBackgroundResource(R.drawable.style_score_gamescreen_secondary);
                            _professorImg.setImageResource(R.drawable.img_question_mark);
                        }
                        if (timeLeft == 10)
                            _timeLeftToAnswerBar.setProgress(90);
                        if (timeLeft == 9)
                            _timeLeftToAnswerBar.setProgress(80);
                        if (timeLeft == 8)
                            _timeLeftToAnswerBar.setProgress(70);
                        if (timeLeft == 7)
                            _timeLeftToAnswerBar.setProgress(60);
                        if (timeLeft == 6)
                            _timeLeftToAnswerBar.setProgress(50);
                        if (timeLeft == 5)
                            _timeLeftToAnswerBar.setProgress(40);
                        if (timeLeft == 4)
                            _timeLeftToAnswerBar.setProgress(30);
                        if (timeLeft == 3)
                            _timeLeftToAnswerBar.setProgress(20);
                        if (timeLeft == 2)
                            _timeLeftToAnswerBar.setProgress(10);
                        if (timeLeft == 1)
                            _timeLeftToAnswerBar.setProgress(0);
                    }
                }
            }

            @Override
            public void onFinish() {
                //restart the timer if the joker has been used, continue if not
                if (_jokerDoubleTimeUsed) {
                    _jokerDoubleTimeUsed = false;
                    _isSecondCountdown = true;
                    _timeLeftToAnswerText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                    _timeLeftToAnswerBar.setBackgroundResource(R.drawable.img_frozen_in_time);
                    _timeLeftToAnswerBar.getProgressDrawable().setColorFilter(Color.parseColor("#82b1ff"), PorterDuff.Mode.SRC_IN);
                    _professorImg.setImageResource(R.drawable.img_cold_mark);
                    _professorImg.setBackgroundResource(0); //remove animationBG
                    startCountDownForQuestion();
                }
                //reset and continue
                else {
                    _professorImg.setBackgroundResource(0); //remove animationBG
                    _jokerTimeBtn.setAlpha(1f);
                    _jokerTimeBtn.setClickable(true);
                    _isSecondCountdown = false;
                    _timeLeftBeforeFreezeJoker = 0;
                    checkUserAnswer(true);
                }
            }
        }.start();
    }

    /**
     * chose catalogue depending on the input on the categoryPage and scoreValuePage and send it to the constructor
     * get a question and remove it until a new game starts to avoid repetition and make it possible to finish this game, when no questions remain
     * if no question left in the category, disable the button
     */
    private void manageQuestionCatalogue() {

        //easy chosen
        if (_questionDifficultyChosen == EASY_DIFFICULTY) {
            switch (_categoryChosen) {
                case ANIMALS_CATEGORY:
                    //set after last question
                    if (_animalsEasyBook.size() == 1) {
                        _disableAnimalsEasyScore = true;
                    }

                    _animalsEasyBook = questionConstructor(_animalsEasyBook);
                    break;
                case BOOKS_CATEGORY:
                    if (_booksEasyBook.size() == 1) {
                        _disableBooksEasyScore = true;
                    }

                    _booksEasyBook = questionConstructor(_booksEasyBook);
                    break;
                case NETFLIX_CATEGORY:
                    if (_netflixEasyBook.size() == 1) {
                        _disableNetflixEasyScore = true;
                    }

                    _netflixEasyBook = questionConstructor(_netflixEasyBook);
                    break;
                case DIGITAL_CATEGORY:
                    if (_digitalEasyBook.size() == 1) {
                        _disableDigitalEasyScore = true;
                    }

                    _digitalEasyBook = questionConstructor(_digitalEasyBook);
                    break;
                case BONUS_CATEGORY:
                    _bonusBook = questionConstructor(_bonusBook);
                    break;
                default:
                    break;
            }
        }
        //hard
        else if (_questionDifficultyChosen == HARD_DIFFICULTY) {
            switch (_categoryChosen) {
                case ANIMALS_CATEGORY:
                    if (_animalsHardBook.size() == 1) {
                        _disableAnimalsHardScore = true;
                    }

                    _animalsHardBook = questionConstructor(_animalsHardBook);
                    break;
                case BOOKS_CATEGORY:
                    if (_booksHardBook.size() == 1) {
                        _disableBooksHardScore = true;
                    }

                    _booksHardBook = questionConstructor(_booksHardBook);
                    break;
                case NETFLIX_CATEGORY:
                    if (_netflixHardBook.size() == 1) {
                        _disableNetflixHardScore = true;
                    }

                    _netflixHardBook = questionConstructor(_netflixHardBook);
                    break;
                case DIGITAL_CATEGORY:
                    if (_digitalHardBook.size() == 1) {
                        _disableDigitalHardScore = true;
                    }

                    _digitalHardBook = questionConstructor(_digitalHardBook);
                    break;
                case BONUS_CATEGORY:
                    _bonusBook = questionConstructor(_bonusBook);
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * responsible for managing the questions displayed and their books
     * <p>
     * using a .xml file containing all the single questions with their answer-possibilities in a single string. (String = "QUESTION:RIGHT_ANSWER:WRONG_ANSWER:WRONG_ANSWER:WRONG_ANSWER", ':' as a separator)
     * every question is stored in an array with other questions belonging to the same category. there is a bookArrayList for every category, difficulty and the bonus questions
     * <p>
     * - first get a random question from the appropriate bookArrayList.
     * - Split the question along its separator ':'
     * - display element 0 to the questionTextView
     * - display the other elements on the radioButtons for the answerPossibilities, in a random order (4 possibilities predefined, so the correct answer could appear anywhere)
     * - remove the question used just now from the book, so it won't be repeated again before a new game has started or the user went gameOver
     * <p>
     * return the questionBook afterwards, used by the QuestionManager
     *
     * @param questionsBook
     * @return
     */
    private ArrayList<String> questionConstructor(ArrayList<String> questionsBook) {
        //create a random generator which goes from 1 to the size of the list, selecting a random question in it
        int max = questionsBook.size();

        Random randomQuestion = new Random();
        int i = randomQuestion.nextInt(max);

        //store the question and split in its components
        //[0] is always the question
        //[1] is always the correct answer
        // while [2], [3] and [4] are the wrong answers
        String questionChosenRaw = questionsBook.get(i);
        String[] questionChosen = questionChosenRaw.split(":");

        //set questionText
        _questionText.setText(questionChosen[0]);

        //fill the radioButtons randomly, so the right answer can appear on any of them
        Random randomOrder = new Random();
        int possibility = randomOrder.nextInt(3);

        //4 different layouts for the order
        switch (possibility) {
            case 0:
                _answerRadioButtonOne.setText(questionChosen[1]); //correct answer on position 1
                _answerRadioButtonTwo.setText(questionChosen[2]);
                _answerRadioButtonThree.setText(questionChosen[3]);
                _answerRadioButtonFour.setText(questionChosen[4]);

                //also assign the radioButtons to be hidden, if the 50/50 Joker is used
                _hiddenByChanceJoker02 = _answerRadioButtonThree;
                _hiddenByChanceJoker01 = _answerRadioButtonTwo;
                _rightAnswerButton = _answerRadioButtonOne;
                break;
            case 1:
                _answerRadioButtonOne.setText(questionChosen[4]);
                _answerRadioButtonTwo.setText(questionChosen[1]); //correct answer on position 2
                _answerRadioButtonThree.setText(questionChosen[2]);
                _answerRadioButtonFour.setText(questionChosen[3]);

                //also assign the radioButtons to be hidden, if the 50/50 Joker is used
                _hiddenByChanceJoker02 = _answerRadioButtonFour;
                _hiddenByChanceJoker01 = _answerRadioButtonOne;
                _rightAnswerButton = _answerRadioButtonTwo;
                break;
            case 2:
                _answerRadioButtonOne.setText(questionChosen[2]);
                _answerRadioButtonTwo.setText(questionChosen[4]);
                _answerRadioButtonThree.setText(questionChosen[1]); //correct answer on position 3
                _answerRadioButtonFour.setText(questionChosen[3]);

                //also assign the radioButtons to be hidden, if the 50/50 Joker is used
                _hiddenByChanceJoker02 = _answerRadioButtonFour;
                _hiddenByChanceJoker01 = _answerRadioButtonTwo;
                _rightAnswerButton = _answerRadioButtonThree;
                break;
            case 3:
                _answerRadioButtonOne.setText(questionChosen[3]);
                _answerRadioButtonTwo.setText(questionChosen[2]);
                _answerRadioButtonThree.setText(questionChosen[4]);
                _answerRadioButtonFour.setText(questionChosen[1]); //correct answer on position 4

                //also assign the radioButtons to be hidden, if the 50/50 Joker is used
                _hiddenByChanceJoker02 = _answerRadioButtonOne;
                _hiddenByChanceJoker01 = _answerRadioButtonThree;
                _rightAnswerButton = _answerRadioButtonFour;
                break;
        }
        //remove the question from the book
        questionsBook.remove(i);

        return questionsBook;
    }

    /**
     * check the user answer on all questions and reacts accordingly
     * <p>
     * stop the questionTimer, if it is no timeOut
     * Update Highscore if it has been broken
     * reset if the answer was incorrect
     * start a delayTimer before displaying the next page
     * and finally display the next page, depending on the state
     *
     * @param timeOut
     */
    private void checkUserAnswer(final boolean timeOut) {

        //disable jokers while delaying next page
        _jokerChanceBtn.setClickable(false);
        _jokerTimeBtn.setClickable(false);
        _answerRadioButtonOne.setClickable(false);
        _answerRadioButtonTwo.setClickable(false);
        _answerRadioButtonThree.setClickable(false);
        _answerRadioButtonFour.setClickable(false);

        //gameOver when the time runs out
        if (timeOut) {

            makeText(getApplicationContext(), getString(R.string.time_out_toast, _questionNumber, _currentScore), Toast.LENGTH_LONG).show();

            //update statistics
            final int newValue;
            switch (_categoryChosen) {
                case BONUS_CATEGORY:
                    newValue = _userPlaying.getInt(USER_BONUS_QUESTION_TIMEOUT_INT, 0) + 1;
                    _editUserPlaying.putInt(USER_BONUS_QUESTION_TIMEOUT_INT, newValue).apply();
                    break;
                case ANIMALS_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_ANIMALS_TIMEOUT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_ANIMALS_TIMEOUT_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_ANIMALS_TIMEOUT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_ANIMALS_TIMEOUT_INT, newValue).apply();
                    }
                    break;
                case BOOKS_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_BOOKS_TIMEOUT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_BOOKS_TIMEOUT_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_BOOKS_TIMEOUT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_BOOKS_TIMEOUT_INT, newValue).apply();
                    }
                    break;
                case NETFLIX_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_NETFLIX_TIMEOUT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_NETFLIX_TIMEOUT_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_NETFLIX_TIMEOUT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_NETFLIX_TIMEOUT_INT, newValue).apply();
                    }
                    break;
                case DIGITAL_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_DIGITAL_TIMEOUT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_DIGITAL_TIMEOUT_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_DIGITAL_TIMEOUT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_DIGITAL_TIMEOUT_INT, newValue).apply();
                    }
                    break;
            }


            _relLayRadioButtonParent.setBackgroundResource(R.drawable.style_button_signal);
            _questionText.setBackgroundResource(R.drawable.style_button_signal);
            _professorImg.setImageResource(R.drawable.img_wrong_mark_prof);
            _professorImg.setBackgroundResource(R.drawable.anim_drawable_prof_on_fire);
            _rightAnswerButton.setBackgroundResource(R.drawable.style_button_highlight);
            resetGlobals();
        }
        //on right answer
        else if (_selectedAnswerButton == _rightAnswerButton) {

            _currentScore = _currentScore + _scoreToAdd;

            int nextQuestion = _questionNumber + 1;

            //on game won
            if (_disableAnimalsEasyScore && _disableAnimalsHardScore
                    && _disableBooksEasyScore && _disableBooksHardScore
                    && _disableNetflixEasyScore && _disableNetflixHardScore
                    && _disableDigitalEasyScore && _disableDigitalHardScore) {

                final int newValue = _userPlaying.getInt(USER_TIMES_WON_INT, 0) + 1;
                _editUserPlaying.putInt(USER_TIMES_WON_INT, newValue).apply();
                makeText(getApplicationContext(), getString(R.string.game_won_toast, _questionNumber, _currentScore), Toast.LENGTH_LONG).show();
                resetGlobals();
            }
            //if the answered question was a bonus questions and answered correctly
            else {
                if (_isBonusQuestion) {
                    _bonusQuestionsAnswered++;

                    makeText(getApplicationContext(), getString(R.string.bonus_right_toast, _scoreToAdd, _scoreToAdd * 2, _bonusQuestionsAnswered, _bonusBook.size() + _bonusQuestionsAnswered), Toast.LENGTH_LONG).show();
                    if (_userPlaying.getInt(USER_HIGHSCORE_INT, 0) < _currentScore) {

                        _editUserPlaying.putInt(USER_HIGHSCORE_INT, _currentScore).apply();
                        _userMenuHS.setText(getString(R.string.txt_points_game, _userPlaying.getInt(USER_HIGHSCORE_INT, 0)));
                    }

                    _currentUserPointsText.setText(String.format("%05d", _currentScore));

                } else {
                    if (_userPlaying.getInt(USER_HIGHEST_SCORE_STREAK_INT, 0) < _questionNumber)
                        _editUserPlaying.putInt(USER_HIGHEST_SCORE_STREAK_INT, _questionNumber).apply();

                    makeText(getApplicationContext(), getString(R.string.right_answer_toast, _questionNumber, nextQuestion, _currentScore), Toast.LENGTH_SHORT).show();
                    _questionNumber++;
                }
            }

            //update statistics
            final int newValue;
            switch (_categoryChosen) {
                case BONUS_CATEGORY:

                    if (_bonusQuestionsAnswered > _userPlaying.getInt(USER_BONUS_HIGHEST_STREAK_INT, 0))
                        _editUserPlaying.putInt(USER_BONUS_HIGHEST_STREAK_INT, _bonusQuestionsAnswered).apply();

                    newValue = _userPlaying.getInt(USER_BONUS_QUESTION_RIGHT_INT, 0) + 1;
                    _editUserPlaying.putInt(USER_BONUS_QUESTION_RIGHT_INT, newValue).apply();
                    break;
                case ANIMALS_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_ANIMALS_RIGHT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_ANIMALS_RIGHT_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_ANIMALS_RIGHT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_ANIMALS_RIGHT_INT, newValue).apply();
                    }
                    break;
                case BOOKS_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_BOOKS_RIGHT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_BOOKS_RIGHT_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_BOOKS_RIGHT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_BOOKS_RIGHT_INT, newValue).apply();
                    }
                    break;
                case NETFLIX_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_NETFLIX_RIGHT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_NETFLIX_RIGHT_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_NETFLIX_RIGHT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_NETFLIX_RIGHT_INT, newValue).apply();
                    }
                    break;
                case DIGITAL_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_DIGITAL_RIGHT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_DIGITAL_RIGHT_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_DIGITAL_RIGHT_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_DIGITAL_RIGHT_INT, newValue).apply();
                    }
                    break;
            }

            _selectedAnswerButton.setBackgroundResource(R.drawable.style_button_highlight);
            _relLayRadioButtonParent.setBackgroundResource(R.drawable.style_button_highlight);
            _questionText.setBackgroundResource(R.drawable.style_button_highlight);
            _professorImg.setImageResource(R.drawable.img_correct_mark);
            _isSecondCountdown = false;
            _countDownTimerQuestion.cancel();

            if (!_isBonusQuestion) {
                if (_userPlaying.getInt(USER_HIGHSCORE_INT, 0) < _currentScore) {

                    _editUserPlaying.putInt(USER_HIGHSCORE_INT, _currentScore).apply();
                    _userMenuHS.setText(getString(R.string.txt_points_game, _userPlaying.getInt(USER_HIGHSCORE_INT, 0)));
                }

                _currentUserPointsText.setText(String.format("%05d", _currentScore));

            }
        }
        //wrong answer given
        else {
            makeText(getApplicationContext(), getString(R.string.wrong_answer_toast, _questionNumber, _currentScore), Toast.LENGTH_LONG).show();
            _selectedAnswerButton.setAlpha(0.5f);
            _rightAnswerButton.setBackgroundResource(R.drawable.style_button_highlight);
            _relLayRadioButtonParent.setBackgroundResource(R.drawable.style_button_signal);
            _questionText.setBackgroundResource(R.drawable.style_button_signal);
            _professorImg.setImageResource(R.drawable.img_wrong_mark_prof);
            _professorImg.setBackgroundResource(R.drawable.anim_drawable_prof_on_fire);
            _countDownTimerQuestion.cancel();

            //update statistics
            final int newValue;
            switch (_categoryChosen) {
                case BONUS_CATEGORY:
                    newValue = _userPlaying.getInt(USER_BONUS_QUESTION_WRONG_INT, 0) + 1;
                    _editUserPlaying.putInt(USER_BONUS_QUESTION_WRONG_INT, newValue).apply();
                    break;
                case ANIMALS_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_ANIMALS_WRONG_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_ANIMALS_WRONG_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_ANIMALS_WRONG_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_ANIMALS_WRONG_INT, newValue).apply();
                    }
                    break;
                case BOOKS_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_BOOKS_WRONG_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_BOOKS_WRONG_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_BOOKS_WRONG_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_BOOKS_WRONG_INT, newValue).apply();
                    }
                    break;
                case NETFLIX_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_NETFLIX_WRONG_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_NETFLIX_WRONG_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_NETFLIX_WRONG_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_NETFLIX_WRONG_INT, newValue).apply();
                    }
                    break;
                case DIGITAL_CATEGORY:
                    if(_questionDifficultyChosen == EASY_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_DIGITAL_WRONG_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_EASY_DIGITAL_WRONG_INT, newValue).apply();
                    }
                    else if(_questionDifficultyChosen == HARD_DIFFICULTY){
                        newValue = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_DIGITAL_WRONG_INT, 0) + 1;
                        _editUserPlaying.putInt(USER_QUANT_CHOOSE_HARD_DIGITAL_WRONG_INT, newValue).apply();
                    }
                    break;
            }

            resetGlobals();

        }


        //start a countdown timer, delaying the display of the next page
        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millSecondsLeftToFinish) {
            }

            @Override
            public void onFinish() {

                if (_selectedAnswerButton != null)
                    _selectedAnswerButton.setAlpha(1f);
                _relLayRadioButtonParent.setBackgroundResource(R.drawable.style_button_regular);
                _questionText.setBackgroundResource(R.drawable.style_button_regular);
                _professorImg.setImageResource(R.drawable.img_question_mark);
                _professorImg.setBackgroundResource(0);
                _jokerChanceBtn.setClickable(false);
                _jokerTimeBtn.setClickable(false);

                //on timeOut
                if (timeOut) {
                    _newGame = true;
                    _isBonusQuestion = false;
                    gameScreenNavigator(QUESTIONS_ASKED_PAGE, START_PAGE);
                }
                //if it's correct
                else if (_rightAnswerButton == _selectedAnswerButton) {
                    //if the game is won
                    if (_disableAnimalsEasyScore && _disableAnimalsHardScore
                            && _disableBooksEasyScore && _disableBooksHardScore
                            && _disableNetflixEasyScore && _disableNetflixHardScore
                            && _disableDigitalEasyScore && _disableDigitalHardScore) {
                        gameScreenNavigator(QUESTIONS_ASKED_PAGE, START_PAGE);
                    } else {

                        //ask for a bonus question, if there is one left
                        if (!_isBonusQuestion && _bonusBook.size() != 0) {
                            gameScreenNavigator(QUESTIONS_ASKED_PAGE, BONUS_QUESTION_CONFIRMATION_PAGE);
                            //let the user select the next question
                        } else {
                            _isBonusQuestion = false;
                            _scoreToAdd = 0;
                            gameScreenNavigator(QUESTIONS_ASKED_PAGE, CATEGORY_SELECTION_PAGE);
                        }

                    }
                }
                //on wrong answer
                else {
                    _newGame = true;
                    _isBonusQuestion = false;
                    gameScreenNavigator(QUESTIONS_ASKED_PAGE, START_PAGE);
                }

            }

        }.start();
    }

    /**
     * reset everything needed on gameOver
     */
    private void resetGlobals() {
        _newGame = true;

        _scoreToAdd = 0;
        _currentScore = 0;
        _bonusQuestionsAnswered = 0;
        _questionNumber = 1;

        _isInMenu = false;
        _disableAnimalsEasyScore = false;
        _disableAnimalsHardScore = false;
        _disableBooksEasyScore = false;
        _disableBooksHardScore = false;
        _disableNetflixEasyScore = false;
        _disableNetflixHardScore = false;
        _disableDigitalEasyScore = false;
        _disableDigitalHardScore = false;
        _jokerDoubleTimeUsed = false;
        _isSecondCountdown = false;
    }


    /**
     * check if the secretQuestion has been answered correctly, returns true on yes - false if not
     *
     * @return
     */
    private boolean secretQuestionAnsweredCorrectly() {
        if (_answerCheckBoxOne.isChecked() && _answerCheckBoxThree.isChecked() && !_answerCheckBoxTwo.isChecked() && !_answerCheckBoxFour.isChecked())
            return true;

        return false;
    }

    /**
     * unlock everything that comes with the achievement! and make sure, that the achievement can't be redone on the same user again
     */
    private void addSecretQuestionAchievement() {
        _editUserPlaying.putBoolean(USER_ANSWERED_SECRET_QUESTION_BOOL, true);
        _editUserPlaying.putInt(USER_ACHIEVEMENT_POINTS_INT, +50);
        _editUserPlaying.apply();

        // textViews for name, achievementPoints and highscore
        TextView achievText = (TextView) findViewById(R.id.vp_start_page_achievements);

        //set values to topMenuBar
        achievText.setText(getString(R.string.txt_points_game, _userPlaying.getInt(USER_ACHIEVEMENT_POINTS_INT, 0)));
    }


    /* *************
    * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
    * |  onClicks        |
    * |________________*/

    /**
     * Reactions to the userInputMain Button on the bottom, depending on the currentPage
     * see comments in method for details
     *
     * @param view
     */
    public void userMainInput(View view) {

        switch (_currentGamePage) {
            /* always navigate to the helpPage from InfoPage */
            case INFO_PAGE:
                gameScreenNavigator(INFO_PAGE, HELP_PAGE);
                break;
            /* navigate from Help Page, either
                directly to the secret question (if triggered as a new User on the ProfileSelectionScreen)
                if opened from the Menu on top, return to the page where the user was before
                if accessed as a new beginner, go to the StartPage */
            case HELP_PAGE:
                if (_userPlaying.getBoolean(USER_TRIGGERED_SECRET_QUESTION_BOOL, false)) {
                    _currentGamePage = PRE_QUESTION_COUNTDOWN_PAGE;
                    _returnTo = START_PAGE;
                    gameScreenNavigator(HELP_PAGE, PRE_QUESTION_COUNTDOWN_PAGE);
                } else if (_isInMenu) {
                    gameScreenNavigator(HELP_PAGE, _returnTo);
                } else if (!_isInMenu)
                    gameScreenNavigator(HELP_PAGE, START_PAGE);
                break;
            /* navigate from Start Page always to CategorySelection Page*/
            case START_PAGE:
                gameScreenNavigator(START_PAGE, CATEGORY_SELECTION_PAGE);
                break;
            /* navigate from CategorySelection Page always to ScoreValueChoice Page*/
            case CATEGORY_SELECTION_PAGE:
                gameScreenNavigator(CATEGORY_SELECTION_PAGE, SCORE_VALUE_CHOICE_PAGE);
                break;
            /* navigate from ScoreValueChoice Page always back to the CategorySelection Page*/
            case SCORE_VALUE_CHOICE_PAGE:
                gameScreenNavigator(SCORE_VALUE_CHOICE_PAGE, CATEGORY_SELECTION_PAGE);
                break;
            /* run method to check the answerInput and hide this the button while waiting for reaction */
            case QUESTIONS_ASKED_PAGE:
                checkUserAnswer(false);
                _userInputBtn.setVisibility(View.INVISIBLE);
                break;
            /* if yes is chosen, start the preCountDown
             * on no, continue on to the CategorySelection Page */
            case BONUS_QUESTION_CONFIRMATION_PAGE:
                if (_isBonusQuestion) {
                    _categoryChosen = BONUS_CATEGORY;
                    gameScreenNavigator(_currentGamePage, PRE_QUESTION_COUNTDOWN_PAGE);
                } else {
                    gameScreenNavigator(QUESTIONS_ASKED_PAGE, CATEGORY_SELECTION_PAGE);
                }
                break;
            /* return to previousPage before the menu was opened */
            case MENU_PAGE:
                gameScreenNavigator(MENU_PAGE, _returnTo);
                _isInMenu = false;
                break;
            /* check the answer and act accordingly */
            case SECRET_QUESTION_PAGE:
                if (secretQuestionAnsweredCorrectly()) {
                    _jokerDoubleTimeUsed = false;
                    _isInMenu = false;
                    addSecretQuestionAchievement();
                    gameScreenNavigator(SECRET_QUESTION_PAGE, _returnTo);
                    _achievementToast.show();
                } else {
                    _jokerDoubleTimeUsed = false;
                    _isInMenu = false;

                    Toast.makeText(getApplicationContext(), R.string.secret_cheat_question_answered_wrong, Toast.LENGTH_LONG).show();
                    gameScreenNavigator(SECRET_QUESTION_PAGE, _returnTo);
                }
                break;
            /* always return to menuPage */
            case STATISTICS_MENU_PAGE:
                gameScreenNavigator(STATISTICS_MENU_PAGE, MENU_PAGE);
                break;
            /* always return to menuPage */
            case FEEDBACK_MENU_PAGE:
                gameScreenNavigator(FEEDBACK_MENU_PAGE, MENU_PAGE);
                break;
            case CHEAT_QUESTION_PAGE:
                manageCheatAnswer();
                break;
            default:
                break;
        }

    }

    /**
     * responsible for starting the secret question while the game is running
     *
     * @param view
     */
    public void toggleSecretQuestionInGame(View view) {
        if (!_userPlaying.getBoolean(USER_ANSWERED_SECRET_QUESTION_BOOL, false)) {
            _secretQuestionTriggered = true;
            _editUserPlaying.putBoolean(USER_TRIGGERED_SECRET_QUESTION_BOOL, true).apply();
            gameScreenNavigator(_currentGamePage, PRE_QUESTION_COUNTDOWN_PAGE);
        }
    }

    /**
     * activates the timeJoker, changing the look of the progressbar and running the timer a second time
     *
     * @param view
     */
    public void jokerDoubleTime(View view) {
        //fit the scoreToAdd accordingly, reducing its baseAmount by a third
        if (_questionDifficultyChosen == EASY_DIFFICULTY) {
            _scoreToAdd = _scoreToAdd - 50;
        }
        if (_questionDifficultyChosen == HARD_DIFFICULTY) {
            _scoreToAdd = _scoreToAdd - 100;
        }
        _jokerDoubleTimeUsed = true;
        _jokerTimeBtn.setAlpha(0.3f);
        _jokerTimeBtn.setClickable(false);
        Toast toast = makeText(getApplicationContext(), R.string.freeze_joker_used_string, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
        toast.show();
    }

    /**
     * activates the chanceJoker, letting two wrong answers fade away
     *
     * @param view
     */
    public void jokerChance(View view) {
        //fit the scoreToAdd accordingly, reducing its baseAmount by a half
        if (_questionDifficultyChosen == EASY_DIFFICULTY) {
            _scoreToAdd = _scoreToAdd - 75;
        }
        if (_questionDifficultyChosen == HARD_DIFFICULTY) {
            _scoreToAdd = _scoreToAdd - 150;
        }
        _jokerChanceBtn.setAlpha(0.3f);
        _jokerChanceBtn.setClickable(false);
        Toast toast = makeText(getApplicationContext(), R.string.chance_joker_used_string, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
        toast.show();

        //mark 2 wrong radioButtons
        _hiddenByChanceJoker01.setAlpha(0.3f);
        _hiddenByChanceJoker01.setBackgroundResource(R.drawable.style_button_signal);
        _hiddenByChanceJoker02.setAlpha(0.3f);
        _hiddenByChanceJoker02.setBackgroundResource(R.drawable.style_button_signal);
        _hiddenByChanceJoker01.setChecked(false);
        _hiddenByChanceJoker02.setChecked(false);
        _hiddenByChanceJoker01.setClickable(false);
        _hiddenByChanceJoker02.setClickable(false);
    }

    /**
     * used for CategoryPage
     * <p>
     * sets the focus to the professor, removing it from the ImageButtons
     *
     * @param view
     */
    public void removeFocusCategorySelection(View view) {

        _titleText.setText(R.string.title_game_category_selection);
        _userInputBtn.setVisibility(View.INVISIBLE);
        _professorImg.requestFocus();
    }

    /**
     * scoreValueSelection-Buttons onClick, starting the countDownPage
     *
     * @param view
     */
    public void startQuestion(View view) {
        //assign variables
        if (view.getId() == R.id.btn_sore_value_high_game_screen)
            _questionDifficultyChosen = HARD_DIFFICULTY;
        else
            _questionDifficultyChosen = EASY_DIFFICULTY;
        //start question
        gameScreenNavigator(SCORE_VALUE_CHOICE_PAGE, PRE_QUESTION_COUNTDOWN_PAGE);
    }

    /**
     * if you are on startPage and didn't answer the secret question, you can activate the cheatMode, giving you 50 AP if you answer the question correctly
     *
     * @param view
     */
    public void activateCheatModeQuestion(View view) {

        switch (_currentGamePage){
            case START_PAGE:
                if (_userPlaying.getInt(USER_ACHIEVEMENT_POINTS_INT, 0) < 50) {
                    if (!_cheatTimerStarted) {

                        _cheatTimerStarted = true;
                        _timesClickedCheat = 0;

                        new CountDownTimer(5000, 1000) {

                            @Override
                            public void onTick(long millSecondsLeftToFinish) {
                                if (_timesClickedCheat >= 4) {
                                    gameScreenNavigator(START_PAGE, CHEAT_QUESTION_PAGE);
                                }
                            }

                            @Override
                            public void onFinish() {
                                _cheatTimerStarted = false;
                                _timesClickedCheat = 0;
                            }
                        }.start();
                    } else _timesClickedCheat++;
                }
                break;
            default:
                break;
        }
    }

    /**
     * checks if the answer inputted is correct
     */
    public void manageCheatAnswer(){
        //get input text
        EditText userAnswer = (EditText) findViewById(R.id.cheat_code_text_answer);
        String correctAnswer = getString(R.string.answer_cheat);

        //if correct
        if (userAnswer.getText().toString().trim().equalsIgnoreCase(correctAnswer)) {
            addSecretQuestionAchievement();
            gameScreenNavigator(CHEAT_QUESTION_PAGE, START_PAGE);
            //unlock statistics button if user is in menu
            Toast.makeText(getApplicationContext(), R.string.cheat_mode_activated, Toast.LENGTH_LONG).show();
        }
        //if wrong
        else
            gameScreenNavigator(CHEAT_QUESTION_PAGE, START_PAGE);
            Toast.makeText(getApplicationContext(), R.string.secret_cheat_question_answered_wrong, Toast.LENGTH_LONG).show();
    }


/* *********************************************************************************************
  * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\
  * |                                                                                              |
  * |  gameMenu and Info/Help                                                                      |
  * |                                                                                              |
  * |    only displayed if the user clicks on the MenuButton or InfoButton in the gameScreen       |
  * |____________________________________________________________________________________________*/
    /* *************
    * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
    * |  displayManager  |
    * |________________*/


    /**
     * manages visibility of the Menu
     * <p>
     * visible on true
     * gone on false
     *
     * @param isVisible
     */
    private void displayMenuMainPage(final boolean isVisible) {

        //Initialize if needed:
        if (_openStatisticsMenuBtn == null)
            _openStatisticsMenuBtn = findViewById(btn_statistics_menu);
        if (_openOptionsMenuBtn == null)
            _openOptionsMenuBtn = findViewById(btn_feedback_menu);
        if (_quitGameMenuBtn == null)
            _quitGameMenuBtn = findViewById(btn_quit_game_menu);


        if (isVisible) {

            _isInMenu = true;
            manageMenuButtons(false);
            manageJokerButtons(false);

            // check if user owns the right to look at the statistics, fade out if not - onClick stays but displays a toast instead
            _openStatisticsMenuBtn.setVisibility(View.VISIBLE);
            if (_userPlaying.getInt(USER_ACHIEVEMENT_POINTS_INT, 0) < 50) {
                _openStatisticsMenuBtn.setAlpha(0.3f);
            }
            _openOptionsMenuBtn.setVisibility(View.VISIBLE);
            _quitGameMenuBtn.setVisibility(View.VISIBLE);

            //correct elements
            _userInputBtn.setText(R.string.btn_back);
            _professorImg.setImageResource(R.drawable.img_menu_mark);
            _bodyText.setText("");
            _titleText.setVisibility(View.GONE);
        }

        if (!isVisible) {
            _openStatisticsMenuBtn.setAlpha(1f);
            _openStatisticsMenuBtn.setVisibility(View.GONE);
            _openOptionsMenuBtn.setVisibility(View.GONE);
            _quitGameMenuBtn.setVisibility(View.GONE);
            _titleText.setVisibility(View.VISIBLE);
            _titleText.setText("");
        }
    }

    /**
     * manages visibility of the StatisticsMenu
     * <p>
     * visible on true
     * gone on false
     *
     * @param isVisible
     */
    private void displayStatisticsMenuPage(final boolean isVisible) {
        if (isVisible) {
            if (_statisticsMenuParent == null)
                _statisticsMenuParent = findViewById(R.id.statistics_menu_page_parent);

            _statisticsMenuParent.setVisibility(View.VISIBLE);
            _professorImg.setVisibility(View.GONE);
            _relLayMenuFrameParent.setVisibility(View.GONE);
            _titleText.setVisibility(View.GONE);
            //activate button listener
            handleStatisticsTabsOnFocus(true);
        }
        if (!isVisible) {
            _statisticsMenuParent.setVisibility(View.GONE);
            _professorImg.setVisibility(View.VISIBLE);
            _relLayMenuFrameParent.setVisibility(View.VISIBLE);
            //remove button listener
            handleStatisticsTabsOnFocus(false);
        }
    }

    /**
     * manages visibility of the FeedbackMenu
     * <p>
     * visible on true
     * gone on false
     *
     * @param isVisible
     */
    private void displayFeedbackMenuPage(final boolean isVisible) {
        if (isVisible) {
            if (_feedbackMenuParent == null)
                _feedbackMenuParent = findViewById(R.id.feedback_menu_page_parent);

            _feedbackMenuParent.setVisibility(View.VISIBLE);
            _professorImg.setVisibility(View.GONE);
            _relLayMenuFrameParent.setVisibility(View.GONE);
            _titleText.setVisibility(View.GONE);
        }
        if (!isVisible) {
            _feedbackMenuParent.setVisibility(View.GONE);
            _professorImg.setVisibility(View.VISIBLE);
            _relLayMenuFrameParent.setVisibility(View.VISIBLE);
        }
    }


    /* *************
    * |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
    * |  onClicks        |
    * |________________*/

    /**
     * Top Right Menu-Buttons, navigates to the Help and Info Pages and returns to the page where it was pressed from
     *
     * @param view
     */
    public void triggerMenus(View view) {
        if (!_isInMenu) {
            _isInMenu = true;
            _returnTo = _currentGamePage;

            if (view.getId() == R.id.btn_help_menu_top)
                gameScreenNavigator(_currentGamePage, INFO_PAGE);
            else {
                gameScreenNavigator(_currentGamePage, MENU_PAGE);
            }
        }
    }

    /**
     * if 50AP have been acquired, opens the statisticsWindow
     * if not, displays an error toast
     *
     * @param view
     */
    public void openStatistics(View view) {
        if (_userPlaying.getInt(USER_ACHIEVEMENT_POINTS_INT, 0) < 50) {
            makeText(getApplicationContext(), R.string.statistics_menu_button_error, Toast.LENGTH_SHORT).show();
        } else
            gameScreenNavigator(MENU_PAGE, STATISTICS_MENU_PAGE);
    }

    /**
     * Either sets or removes the onFocusListener for the tab buttons on top of the statistics menu
     * the listeners also handle the content of the TextViews and their visibility, via shredPrefs
     *
     * @param isNeeded
     */
    private void handleStatisticsTabsOnFocus(final boolean isNeeded) {

        //get the buttons
        final ImageButton statisticsGeneral = (ImageButton) findViewById(R.id.img_btn_statistics_general);
        final ImageButton statisticsA = (ImageButton) findViewById(R.id.img_btn_statistics_animals);
        final ImageButton statisticsB = (ImageButton) findViewById(R.id.img_btn_statistics_books);
        final ImageButton statisticsN = (ImageButton) findViewById(R.id.img_btn_statistics_netflix);
        final ImageButton statisticsD = (ImageButton) findViewById(R.id.img_btn_statistics_digital);

        //enable them
        if (isNeeded) {

            //get all the stats needed from sharedPrefs and fill texts with them
            //general tab stats
            final int genLogin = _userPlaying.getInt(USER_TIMES_LOGGED_IN_INT, 0);
            final int genStreak = _userPlaying.getInt(USER_HIGHEST_SCORE_STREAK_INT, 0);
            final int genTimesWon = _userPlaying.getInt(USER_TIMES_WON_INT, 0);
            final int genBonusStreak = _userPlaying.getInt(USER_BONUS_HIGHEST_STREAK_INT, 0);
            final int genBonusRight = _userPlaying.getInt(USER_BONUS_QUESTION_RIGHT_INT, 0);
            final int genBonusWrong = _userPlaying.getInt(USER_BONUS_QUESTION_WRONG_INT, 0);
            final int genBonusTimeOut = _userPlaying.getInt(USER_BONUS_QUESTION_TIMEOUT_INT, 0);

            //category stats
            //Category A easy
            final int animalsRightEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_ANIMALS_RIGHT_INT, 0);
            final int animalsWrongEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_ANIMALS_WRONG_INT, 0);
            final int animalsTimeOutEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_ANIMALS_TIMEOUT_INT, 0);
            //Category A hard
            final int animalsRightHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_ANIMALS_RIGHT_INT, 0);
            final int animalsWrongHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_ANIMALS_WRONG_INT, 0);
            final int animalsTimeOutHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_ANIMALS_TIMEOUT_INT, 0);

            //Category B easy
            final int booksRightEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_BOOKS_RIGHT_INT, 0);
            final int booksWrongEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_BOOKS_WRONG_INT, 0);
            final int booksTimeOutEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_BOOKS_TIMEOUT_INT, 0);
            //Category B hard
            final int booksRightHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_BOOKS_RIGHT_INT, 0);
            final int booksWrongHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_BOOKS_WRONG_INT, 0);
            final int booksTimeOutHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_BOOKS_TIMEOUT_INT, 0);

            //Category N easy
            final int netflixRightEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_NETFLIX_RIGHT_INT, 0);
            final int netflixWrongEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_NETFLIX_WRONG_INT, 0);
            final int netflixTimeOutEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_NETFLIX_TIMEOUT_INT, 0);
            //Category N hard
            final int netflixRightHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_NETFLIX_RIGHT_INT, 0);
            final int netflixWrongHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_NETFLIX_WRONG_INT, 0);
            final int netflixTimeOutHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_NETFLIX_TIMEOUT_INT, 0);

            //Category D easy
            final int digitalRightEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_DIGITAL_RIGHT_INT, 0);
            final int digitalWrongEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_DIGITAL_WRONG_INT, 0);
            final int digitalTimeOutEasy = _userPlaying.getInt(USER_QUANT_CHOOSE_EASY_DIGITAL_TIMEOUT_INT, 0);
            //Category D hard
            final int digitalRightHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_DIGITAL_RIGHT_INT, 0);
            final int digitalWrongHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_DIGITAL_WRONG_INT, 0);
            final int digitalTimeOutHard = _userPlaying.getInt(USER_QUANT_CHOOSE_HARD_DIGITAL_TIMEOUT_INT, 0);

            //totals for the general tabs stats
            final int genTotalRight = animalsRightEasy + animalsRightHard + booksRightEasy + booksRightHard + netflixRightEasy + netflixRightHard + digitalRightEasy + digitalRightHard + genBonusRight;
            final int genTotalWrong = animalsWrongEasy + animalsWrongHard + booksWrongEasy + booksWrongHard + netflixWrongEasy + netflixWrongHard + digitalWrongEasy + digitalWrongHard + genBonusWrong;
            final int genTotalTimeOut = animalsTimeOutEasy + animalsTimeOutHard + booksTimeOutEasy + booksTimeOutHard + netflixTimeOutEasy + netflixTimeOutHard + digitalTimeOutEasy + digitalTimeOutHard + genBonusTimeOut;
            final int genTotalBonusQuestions = genBonusRight + genBonusWrong + genBonusTimeOut;
            final int genTotalQuestions = genTotalRight + genTotalWrong + genTotalTimeOut;

            //totals for the categorys
            // A
            final int totalEasyAnimals = animalsRightEasy + animalsWrongEasy + animalsTimeOutEasy;
            final int totalHardAnimals = animalsRightHard + animalsWrongHard + animalsTimeOutHard;
            final int totalAnimals = totalEasyAnimals + totalHardAnimals;
            // B
            final int totalEasyBooks = booksRightEasy + booksWrongEasy + booksTimeOutEasy;
            final int totalHardBooks = booksRightHard + booksWrongHard + booksTimeOutHard;
            final int totalBooks = totalEasyBooks + totalHardBooks;
            // N
            final int totalEasyNetflix = netflixRightEasy + netflixWrongEasy + netflixTimeOutEasy;
            final int totalHardNetflix = netflixRightHard + netflixWrongHard + netflixTimeOutHard;
            final int totalNetflix = totalEasyNetflix + totalHardNetflix;
            // D
            final int totalEasyDigital = digitalRightEasy + digitalWrongEasy + digitalTimeOutEasy;
            final int totalHardDigital = digitalRightHard + digitalWrongHard + digitalTimeOutHard;
            final int totalDigital = totalEasyDigital + totalHardDigital;

            //get text elements
            final TextView titleTextStats = (TextView) findViewById(R.id.txt_title_statistics_menu);
            final TextView generalTextStats = (TextView) findViewById(R.id.txt_general_statistics_menu);
            //top details
            final TextView topSubheaderStats = (TextView) findViewById(R.id.txt_statistics_a_top);
            final TextView topRightStats = (TextView) findViewById(R.id.txt_statistics_a_right);
            final TextView topWrongStats = (TextView) findViewById(R.id.txt_statistics_a_wrong);
            final TextView topTimeoutStats = (TextView) findViewById(R.id.txt_statistics_a_time_out);
            //bottom details
            final TextView bottomSubheaderStats = (TextView) findViewById(R.id.txt_statistics_b_top);
            final TextView bottomRightStats = (TextView) findViewById(R.id.txt_statistics_b_right);
            final TextView bottomWrongStats = (TextView) findViewById(R.id.txt_statistics_b_wrong);
            final TextView bottomTimeoutStats = (TextView) findViewById(R.id.txt_statistics_b_time_out);

            //default page
            statisticsGeneral.setAlpha(0.3f);
            statisticsA.setAlpha(1f);
            statisticsB.setAlpha(1f);
            statisticsN.setAlpha(1f);
            statisticsD.setAlpha(1f);

            //show needed views
            titleTextStats.setVisibility(View.GONE);
            generalTextStats.setVisibility(View.VISIBLE);

            //fill views
            generalTextStats.setText(getString(R.string.main_statistics_general, String.format("%03d", genLogin), String.format("%03d", genTimesWon), String.format("%05d", _userPlaying.getInt(USER_HIGHSCORE_INT, 0))));

            topSubheaderStats.setText(getString(R.string.statistics_questions_all, genTotalQuestions, genStreak));
            topRightStats.setText("" + genTotalRight);
            topWrongStats.setText("" + genTotalWrong);
            topTimeoutStats.setText("" + genTotalTimeOut);

            bottomSubheaderStats.setText(getString(R.string.statistics_questions_bonus, genTotalBonusQuestions, genBonusStreak));
            bottomRightStats.setText("" + genBonusRight);
            bottomWrongStats.setText("" + genBonusWrong);
            bottomTimeoutStats.setText("" + genBonusTimeOut);

            //set listeners
            statisticsGeneral.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    //simulate tab behaviour
                    v.setAlpha(0.3f);
                    statisticsA.setAlpha(1f);
                    statisticsB.setAlpha(1f);
                    statisticsN.setAlpha(1f);
                    statisticsD.setAlpha(1f);

                    //show needed views
                    titleTextStats.setVisibility(View.GONE);
                    generalTextStats.setVisibility(View.VISIBLE);

                    //fill views
                    generalTextStats.setText(getString(R.string.main_statistics_general, String.format("%03d", genLogin), String.format("%03d", genTimesWon), String.format("%05d", _userPlaying.getInt(USER_HIGHSCORE_INT, 0))));

                    topSubheaderStats.setText(getString(R.string.statistics_questions_all, genTotalQuestions, genStreak));
                    topRightStats.setText("" + genTotalRight);
                    topWrongStats.setText("" + genTotalWrong);
                    topTimeoutStats.setText("" + genTotalTimeOut);

                    bottomSubheaderStats.setText(getString(R.string.statistics_questions_bonus, genTotalBonusQuestions, genBonusStreak));
                    bottomRightStats.setText("" + genBonusRight);
                    bottomWrongStats.setText("" + genBonusWrong);
                    bottomTimeoutStats.setText("" + genBonusTimeOut);

                }
            });

            statisticsA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    //simulate tab behaviour
                    statisticsGeneral.setAlpha(1f);
                    v.setAlpha(0.3f);
                    statisticsB.setAlpha(1f);
                    statisticsN.setAlpha(1f);
                    statisticsD.setAlpha(1f);

                    //show needed views
                    titleTextStats.setVisibility(View.VISIBLE);
                    generalTextStats.setVisibility(View.GONE);

                    titleTextStats.setText(getString(R.string.title_animal_category_statistic, totalAnimals));

                    topSubheaderStats.setText(getString(R.string.statistics_questions_easy, totalEasyAnimals));
                    topRightStats.setText("" + animalsRightEasy);
                    topWrongStats.setText("" + animalsWrongEasy);
                    topTimeoutStats.setText("" + animalsTimeOutEasy);

                    bottomSubheaderStats.setText(getString(R.string.statistics_questions_hard, totalHardAnimals));
                    bottomRightStats.setText("" + animalsRightHard);
                    bottomWrongStats.setText("" + animalsWrongHard);
                    bottomTimeoutStats.setText("" + animalsTimeOutHard);
                }
            });

            statisticsB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    //simulate tab behaviour
                    statisticsGeneral.setAlpha(1f);
                    statisticsA.setAlpha(1f);
                    v.setAlpha(0.3f);
                    statisticsN.setAlpha(1f);
                    statisticsD.setAlpha(1f);

                    //show needed views
                    titleTextStats.setVisibility(View.VISIBLE);
                    generalTextStats.setVisibility(View.GONE);

                    titleTextStats.setText(getString(R.string.title_books_category_statistic, totalBooks));

                    topSubheaderStats.setText(getString(R.string.statistics_questions_easy, totalEasyBooks));
                    topRightStats.setText("" + booksRightEasy);
                    topWrongStats.setText("" + booksWrongEasy);
                    topTimeoutStats.setText("" + booksTimeOutEasy);

                    bottomSubheaderStats.setText(getString(R.string.statistics_questions_hard, totalHardBooks));
                    bottomRightStats.setText("" + booksRightHard);
                    bottomWrongStats.setText("" + booksWrongHard);
                    bottomTimeoutStats.setText("" + booksTimeOutHard);
                }
            });

            statisticsN.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    //simulate tab behaviour
                    statisticsGeneral.setAlpha(1f);
                    statisticsA.setAlpha(1f);
                    statisticsB.setAlpha(1f);
                    v.setAlpha(0.3f);
                    statisticsD.setAlpha(1f);

                    //show needed views
                    titleTextStats.setVisibility(View.VISIBLE);
                    generalTextStats.setVisibility(View.GONE);

                    titleTextStats.setText(getString(R.string.title_netflix_category_statistic, totalNetflix));

                    topSubheaderStats.setText(getString(R.string.statistics_questions_easy, totalEasyNetflix));
                    topRightStats.setText("" + netflixRightEasy);
                    topWrongStats.setText("" + netflixWrongEasy);
                    topTimeoutStats.setText("" + netflixTimeOutEasy);

                    bottomSubheaderStats.setText(getString(R.string.statistics_questions_hard, totalHardNetflix));
                    bottomRightStats.setText("" + netflixRightHard);
                    bottomWrongStats.setText("" + netflixWrongHard);
                    bottomTimeoutStats.setText("" + netflixTimeOutHard);
                }
            });

            statisticsD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //does nothing without focus
                    if (!hasFocus)
                        return;

                    statisticsGeneral.setAlpha(1f);
                    statisticsA.setAlpha(1f);
                    statisticsB.setAlpha(1f);
                    statisticsN.setAlpha(1f);
                    v.setAlpha(0.3f);

                    //show needed views
                    titleTextStats.setVisibility(View.VISIBLE);
                    generalTextStats.setVisibility(View.GONE);

                    titleTextStats.setText(getString(R.string.title_digital_category_statistic, totalDigital));

                    topSubheaderStats.setText(getString(R.string.statistics_questions_easy, totalEasyDigital));
                    topRightStats.setText("" + digitalRightEasy);
                    topWrongStats.setText("" + digitalWrongEasy);
                    topTimeoutStats.setText("" + digitalTimeOutEasy);

                    bottomSubheaderStats.setText(getString(R.string.statistics_questions_hard, totalHardDigital));
                    bottomRightStats.setText("" + digitalRightHard);
                    bottomWrongStats.setText("" + digitalWrongHard);
                    bottomTimeoutStats.setText("" + digitalTimeOutHard);
                }
            });

            //always show generalTab first
            statisticsGeneral.requestFocus();
        }
        //disable
        else if (!isNeeded) {
            statisticsGeneral.setOnFocusChangeListener(null);
            statisticsA.setOnFocusChangeListener(null);
            statisticsB.setOnFocusChangeListener(null);
            statisticsN.setOnFocusChangeListener(null);
            statisticsD.setOnFocusChangeListener(null);
        }
    }

    /**
     * opens the feedbackWindow
     *
     * @param view
     */
    public void openFeedbackMenu(View view) {
        gameScreenNavigator(MENU_PAGE, FEEDBACK_MENU_PAGE);
    }

    /**
     * exit the application
     *
     * @param view
     */
    public void closeApplication(View view) {
        finish();
        System.exit(0);
    }

    /**
     * start a Mail-Intent with predefined text for bug reports
     *
     * @param view
     */
    public void reportBug(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getResources().getString(R.string.feedback_recipient_mail)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.mail_subject_bug_report));
        intent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.mail_bug_predefined_text));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * start a Mail-Intent for suggestions
     *
     * @param view
     */
    public void sendSuggestion(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getResources().getString(R.string.feedback_recipient_mail)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.mail_subject_suggestion));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * just displays a Toast ;)
     *
     * @param view
     */
    public void giveMoney(View view) {
        Toast soNiceOfYou = Toast.makeText(getApplicationContext(), R.string.send_money_blush, Toast.LENGTH_LONG);
        soNiceOfYou.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
        soNiceOfYou.show();
    }

}