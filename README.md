
# Worlde

#### Install

Use git to clone this repository into your computer.

> git clone https://gitlab.com/ivan.martinez.7e6/wordle

---

## Formatting the Output

In this project we will make use of variables to
color the foreground and background of each character
when needed.

### Usage

* Write the name or names of the constants you want
to use before the next word or character
* Use the variable `reset` after the desired word or character to end the formatting

---

## How To Play

- The user has 6 tries to guess the right word
- Each word has 5 characters
- The characters of the word doesn't repeat themselves
- If the foreground of the character of the guessed word
changes to **gray** it means it doesn't exist in the word.
- If the foreground changes to **yellow** it means it exists in
the word but is not in the correct position.
- If the foreground changes to **green** it means it is in the
correct position.

---

### Code

The ***instruccions*** function will explain the user how to play
and will ask the user to type ***START*** to begin.

#### Game

The ***codi*** function is where the word pool of the game and all the logic of the application lies.
- First will take a random word from the `wordPool` and will ask the user for a Word
- If the word has repeated chars or doesn't have a length of 5 chars it will throw a warning and will ask
for a word again.

##### When the word is accepted and isn't the right guess

- It will scan every char at the `userGuess` word and compare
the position with the same position of the char of the random
word.
- At the same time will be colored based on the position and added
to a variable called `history`
- Then it will be added to a list called `historyList` and with each iteration will print the content
of the list creating the history of the game
- Last it will rest 1 try

##### When the word is the right word

Will print a congratulations message with the correct word

#### After the game

If the tries reach 0 or the user guesses the word it will ask if the user wants to continue playing,
read the rules or stop playing.

### License
