# MediaNode

MediaNode is an application which lets the user track all kinds of media (e.g. Books, Music, Games, Movies,...) they already consumed or plan to consume. This project was hugely inspired by the websites <a href="https://backloggd.com">Backloggd</a> and <a href="https://anilist.co/home">AniList</a>.

# 📸 Preview

<img src="/docs/img/HomeView.png">

<img src="/docs/img/DetailsView.png">

>[!NOTE]
>The design is still unfinished. More (small) changes will be made in future

# :sparkles: Features 

- Creating an account
- SignIn/SignOut
- Browsing media (that is stored in the database)
- Searching for a medium with a search field
- Viewing details from medias
- Creating an entry either with the status `PLANNING` OR `CONSUMING` OR `COMPLETED`
- Assign ratings for a specific media
- View your activity log at your profile

# Prerequisities
- Docker Desktop
- JDK version 17 or higher

# Installation

1. Clone the repository:
```zsh 
https://github.com/Armin-Keric/MediaNode.git
```

2. Go to your Terminal and type in the following command
```zsh 
docker compose up -d
```
>[!NOTE]
Make sure Docker Desktop is running. The command should start the `medianode` container automatically.

3. Start the program



# :rocket: Tech Stack
- JavaFX 
- Java
- PostgreSQL
- Docker

# :sunglasses: Authors

<ul>
<li><a href="https://github.com/Armin-Keric">Armin Keric</a></li>
<li><a href="https://github.com/sgerdeneb">Ganerdene Erdenebayar</a></li>
<li><a href="https://github.com/Luhukas">Lukas Radlspöck</a></li>
</ul>
