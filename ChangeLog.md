ChangeLog
=========

Version 4.0
-----------
- ``RC.1`` Implemented missing tests
- ``B.3`` Implementation and testing of WeaponInventory class (ensured 100% branch coverage)
- ``B.2`` Implementation and testing of EnemyParty class(ensured 100% branch coverage)
- ``B.1`` Implementation and testing of PlayerParty class(ensured 100% branch coverage)

Version 3.0
-----------
- ``RC.1`` Implemented missing tests (ensured 100% branch coverage)
- ``B.4`` Updated the old tests (ensured)
- ``B.3`` Implemented waitTurn() on the AbstractCharacter abstract class
- ``B.2`` Removed waitTurn() on the PlayerCharacter and Enemy classes
- ``B.1`` Added abstract method getWeight() on the AbstractCharacter abstract class

Version 2.0
-----------
- ``RC.1`` Added and updated the methods descriptions
- ``B.8`` Added methods on the PlayerCharacter class (added equip(Weapon), getEquippedWeapon() and getCharacterClass())
- ``B.7`` Implemented waitTurn() method on PlayerCharacter and Enemy classes
- ``B.6`` Updated the constructor on PlayerCharacter and Enemy classes 
- ``B.5`` Removed ENEMY from CharacterClass
- ``B.4`` Implemented the ICharacter methods on the AbstractCharacter abstract class
- ``B.3`` Removed methods on the AbstractCharacter abstract class ``Removed equip(Weapon), getEquippedWeapon() and getCharacterClass())
- ``B.2`` Removed methods on the ICharacter interface ``Removed equip(Weapon), getEquippedWeapon() and getCharacterClass())
- ``B.1`` Added methods on the ICharacter interface (added addToQueue(), getHealthPoints() and getDefensePoints())

Version 1.0
-----------
- ``RC.1`` Implemented missing tests
- ``B.5`` Updated License
- ``B.4`` Implementation and testing of Enemy class (ensured 100% branch coverage)
- ``B.3`` Created .gitignore
- ``B.2`` Implementation of most base elements of the model
- ``B.1`` Created project

A note on the version naming
----------------------------
- B.n: Version ``x.y`` _beta x_, alternative to ``x.y-b.n``.
  For example: ``v1.0-b.3``.
- RC.n: Release candidate x, alternative to ``x.y-rc.n``.
  For example: ``v1.0-rc.2``.