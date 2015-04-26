# Quiz
PiJ - Quiz Coursework


Programming in Java
Coursework assignment 5 — A Quiz System 2014-15

How to play:
1. Start/run ServerApp (only if not already started)
2. Start/run ClientTest (multiple clients are permitted)

You'll be presented with a list of available quizzes and prompted to choose one from the list to play.

3. Choose a quiz to play.

You'll be presented with the questions and the answer options, one question at a time, for the quiz selected.

4. Answer each question as instructed.

You will be given immediate feedback as to whether each of your answers is correct, or not. 

At the end of the quiz, you will be given feedback on your score.
Your quiz score will be persisted to file.

You will be asked to submit your name and username details. These details, along with your quiz score, will also be persisted to file.

NB. The application uses two JSON files to hold those details which we were instructed to persist in the assignment brief. This will require JSON to be installed on the assignment assessor's machine.
I have submitted two files: Quizes.json and HighScoreList.json with the required structure. These files should both reside directly in the Server folder.

How to submit a new quiz:
1. Start/run ServerApp (only if not already started)
2. Start/run ClientQuizAdmin (multiple clients are permitted)

You'll be presented with the option either to submit a new quiz, or close an existing one.

3. Choose to submit a new quiz.

4. Submit your quiz questions and associated answers, as instructed.


How to close a quiz:
1. Start/run ServerApp (only if not already started)
2. Start/run ClientQuizAdmin (multiple clients are permitted)

You'll be presented with the option either to submit a new quiz, or close an existing one.

3. Choose to close an existing quiz.

You'll be prompted to enter the ID of the quiz you wish to close. (sorry I forgot to bring up a proper list of quizzes untilit was too late to code it, so you'll have to remember which QuidID to delete)

4. Choose the quiz you wish to close.

You'll be presented with the summary details of:
a. the highest score for this quiz and 
b. the details of the player, or players, attaining that score.

The quiz questions and answers will be removed from file containing the quizzes.
NB The Quiz specification is unclear as to whether the quiz closure summary details should be persisted, or removed. I have chosen to persist that information. It is therefore not removed from file. 





1 The problem description
You are asked to design and implement a simple on-line quiz game using Java RMI. This
assignment is to be carried out individually.

2 The quiz game
The quiz game system should consist of a central quiz server and two separate client
programs: a set-up client and a player client.
The set-up client program should enable a user who wishes to organise an electronic
quiz game to create a new quiz (i.e., a set of questions) on the server, with a given name
for the quiz, and a set of possible answers for each question. This will return a quiz game
id. At some point in the future, this client should be able to close the quiz game, quoting
the game id. The outcome will be a notification of the winner together with full player
details (which should be persisted on the server).
The player client program should enable players to play one of quizzes available on
the server.
• Firstly, the program should enable players to see a list of current quizzes and select one.
• The players should then be able to answer each question of the quiz by choosing their answer amongst those suggested (i.e., multiple-choice).
• At the end of the quiz the quiz server should return the score of the player for this quiz.

The quiz game server should deal with (potentially concurrent) requests from the two client programs and maintain the state of the various ongoing quizzes in a consistent manner. 

The deliverables
The assignment must be pushed to a project called Quiz on your GitHub account. It will be automatically cloned on the due date. You should upload your classes, documentation, and unit tests, in your submission. 

You should include full javadoc for your classes, and a README file detailing how to run your system.