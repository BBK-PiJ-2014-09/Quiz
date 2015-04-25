# Quiz
Pij - Quiz Coursework


Programming in Java
Coursework assignment 5 — A Quiz System 2014-15

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