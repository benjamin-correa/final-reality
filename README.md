Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control a group of 
enemies controlled by the computer.

---

Features
-------

This game is following the model-view-controller software design pattern, and the SOLID principles (for more details, read the change log).

1. Implemented the model.  

    1. Separate classes for the _player characters_ and the _enemies_.
    
        1. The _player classes_ implements the same interface.  
        
        2. The _player classes_ are divided in two: 
        
            1. Common _player classes_:  
                * Thief.
                * Engineer.
                * Knight.
              
            2. Mage _player classes_:
            
                * This classes implements _mana points_.
                    * Black Mage.
                    * White Mage.
                
            3. Both _player classes_ can equip weapons.
            
                * _Thief_ can equip a sword, knife or a bow.
                * _Knight_ can equip a sword, axe or knife.
                * _Engineer_ can equip an axe or a bow.
                * _Black_ mage can equip a knife or a staff.
                * _White_ mage can equip only a staff.
                
            4. The weapon can be changed in the player turn.
                * When the _player character_ sets a weapon it can't be changed.
                
        3. The _enemies_ cannot equip weapons.
            * They have their own weight.
            
    2. The game is played by turns.
    
        1. The time that each character waits to play is calculated by their weight.
        
            * The _player characters_ weight is calculated by their weapon weight.
            * Once a character attack, immediately enters to the queue.
            * The minimum wait time on the queue is (weight/10).
            
    3. There are two types of weapons.
        1. Common weapons:
            * Knife.
            * Sword.
            * Bow.
            * Axe.
            
        2. Magic weapons:
            * These weapons have an extra magic damage attribute.
                * Staff
        
2. Controller.
    
    * The controller is an intermediary between the player, and the model.
    
    * Can change and interact with the model.
        
        1. Can create player characters, enemies and weapons, and assign these to their respective list.
        
        2. Can gather information of the model objects.
        
        3. Can manipulate the queue and the turn of each character.
        
        4. Knows when a player has won or lost.
    
    
3. View.
    
    * It has 6 different scenes:
        
        1. Settings of the game:
    
            * We can set the players party size.
            
            * We can create players characters with random stats.
    
            * We can randomize the enemies that we will face (1-4 enemies with random stats).
    
        2. Player's turn:
            
            * We can choose a weapon to equip.
            
            * We can attack an enemy character.
    
            * We can see the enemy's new health points.
    
        3. Enemy's turn:
        
            * The enemy selects a random player character to attack.
    
            * We can see the new player character health points.
    
        4. Waiting for characters:
        
            * We wait the queue to have at least one character on it.
    
        5. Players win:
            
            * Displays a text and an image when the player win.
    
        6. Enemies win:
            
            * Displays a text and an image when thw enemies win.


Running the game
----------------

To run the game we have to do the following things.

1. Run ``FinalReality.java``.

2. Select the desired party size.

3. Create the player characters.

4. Choose your random enemies.

5. Start to play.

6. Two things con happen here:
   
    1. Is an enemy turn:
   
        * You can see the attack recap and pass pressing the button.
    
    2. Is a player turn:
        
        * You have to choose a weapon to equip and confirm it.
    
        * Now you have to choose an enemy to attack.
    
        * You can see the attack recap and end your turn.
    
7. If the queue is empty you enter to a waiting screen that disappears when the queue is not empty.

8. We will see who wins!!