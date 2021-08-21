# ~~Abyssalith, The Discord Bot~~ (MIGRATED) [Here](https://github.com/VolmitSoftware/Abyssalith)

This bot's repository is just a repo for a small server bot being that can do things. This is using Naming conventions from [Volmit Software](https://github.com/VolmitSoftware) This bot is only showing off the JDA at the current time, and making a bot to learn it. You are more than welcome to keep this code for whatever you want as this is just a proof of concept.

## Getting Started

**This should help you get setup using this bot:**

- When you compule the bot, and execute the bot(as a jar. Use a `.bat` file), Make this your config file
- You need to use Gradlew shadowJar
- Location of config file: `config/toolkit.json` in a folder next to your bot
```json
{
    "ModRole": "",
    "AdminRole": "",
    "BotGIF": "",
    "BotColor": "",
    "BotToken": "",
    "BotOwnerID": "",
    "BotPrefix": "",
    "MoneyEmoji": "",
    "MoneyName": ""
}
```

### Prerequisites

You need Gradle, Java 16, an internet connection, and some IDE(would suggest intelij). Good luch with that however :)

### Installing

- You can clone this repo and import the project in any Java IDE that you may want to use. This project was made using intelik, but any should work as long as it supports gradle
- Make sure that you have imported the plugins required via the Gradle file (look up how to do that for your own environment) and that your IDE recognize the folder `java` as Source folder and `resources` as Resources folder.
If you have trouble getting the bot up and running, feel free to [create an issue](https://github.com/NextdoorPsycho/Abyssalith/issues).

### Setting up your environment (Bot Token)

Since this is a self-hosted solution, you will need to provide your own Bot Token to get your bot up and running.

If you don't know how to get your own bot token, you can follow [these instructions](https://github.com/reactiflux/discord-irc/wiki/Creating-a-discord-bot-&-getting-a-token) to create yourself one.

Once you have your bot token, you can run the program for the first time. 
Your `config/toolkit.json` file should look like this :
```json
{
    "ModRole": "Moderator",
    "AdminRole": "Admin",
    "BotGIF": "https://i.imgur.com/OdIvsbK.gif",
    "BotColor": "0xFFFF00",
    "BotToken": "haveatokenhere",
    "BotOwnerID": "00000000000000",
    "BotPrefix": "!",
    "MoneyEmoji": ":coin:",
    "MoneyName": "LitCoins"
}
```
The configuration file hotloads, and when you make any changes it should spit into the console that it hotloaded.

### Config Breakdown
    "ModRole": 
    - This is just a moderator role
    "AdminRole": 
    - This is just a administrator role
    "BotGIF":
    - This is the gif that is placed at the footer of the bot
    "BotColor":
    - `0x000000` hex number that the bot uses eg:`0x000000`
    "BotToken": 
    - the bots secret token here
    "BotOwnerID":
    - the id for whoever is the hosted owner of the bot
    "BotPrefix":
    - bot prefix
    "MoneyEmoji": 
    - For the economy, this is the server's emoji
    "MoneyName": 
    - name of your made up currency eg: `Pound sterling's`


## Built With

- [JDA](https://github.com/DV8FromTheWorld/JDA) - The Java Discord APIs to allow a bot to run in Java
- [Gradle](https://gradle.org/) - Dependency Management

## Authors

- Brian Fopiano ([NextdoorPsycho](https://github.com/NextdoorPsycho)) - Original Creator and Owner of the softworks
- 
See also the list of [contributors](https://github.com/NextdoorPsycho/Abyssalith/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
