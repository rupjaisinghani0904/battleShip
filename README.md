[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/tqZ-4RLU)
# 3500 PA04 - BattleSalvo - Part 2 Project Repo

[PA04 Write-Up](https://markefontenot.notion.site/PA-04-BattleSalvo-Part-2-20ff66267da84956b35794bf8452c2fd)

This template includes several additional tools:
1. Gradle Build Automation
1. JaCoCo for Test Coverage
1. CheckStyle for Code Style Checks (Using the custom [cs3500 check file](./config/checkstyle/cs3500-checkstyle.xml)) 

Changes from Pa03
- Changed setShips method for Board to update the 2d list of coords directly
- Created getter and setter for starting coordinate, field for ship direction
- Changed how ships were setup based on starting coord
- Improved AI Player takeshots
- Chnaged reportDamage and sucessfulHits in abstractplayer to to transfer appriopriate data (before there was a bug that caused improper data transfer)
