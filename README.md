# BotBase
The bot base for all bots made by Shockbyte

## About
This is the bot base that we use over at [Shockbyte](https://shockbyte.com) \for our [Discord Server](https://discord.gg/7epCUD4). This is developed and maintained by the [Shockbyte Team](https://github.com/Shockbyte).

This lib helps with basic bot setup and with commands, you don't need to setup commands or deal with login you can just use this lib instead. It also has utils for things like getting what a user has inputted form just the args.

## Usage

### Setup config file
Making and using the configuration is very simple, all you need to do is create a new JSONConfig with the config name (and location is applicable). Then all you need to do is get something from the specific path where . equals an inner object. For example, if it is "bot.token" it will look for a "bot" object and go for "token". This returns an Optioanl so you can check if it exists and also get the value (or else return another).

```java
public void init() {
    JSONConfig config = new JSONConfig("config.json");
    
    if (config.getString("bot.token").isPresent())
        init(config.getString("bot.token").get());
    else {
        throw new IllegalStateException("You need the token in order to start the bot!");
        System.exit(1);
    }
}
```

### How to start the bot
Starting the bot is super simple with this bot base, all you need to do is extend Bot in the main class and use the `init` method. You then need to override the run method which is an `abstract void` in Bot. This is fired when the bot is ready (ReadyEvent fired), this is very useful for things that are not just used in init.

```java
public void init() {
    init(config.getString("bot.token").get(), config.getString("bot.prefix").get());
}

@Override
public void run() {
    // Do some stuff
}
```

### How to setup and use MySQL
If you wish to connec to NySQL and execute queries in that it is very simple, all you need to do is in your initialising method where you init the bot hire `setupMySQL` with your host, port, username and password. **Do not hardcode these details, get them from some sort of config file or environment variables.**

```java
public void init() {
    setupMySQL(config.getString("mysql.host").get(), config.getInt("mysql.port").get(), config.getString("mysql.username").get(), config.getString("mysql.password").get());
}
```

## How to use
If you wish to use this for your own bot then all you need to do is simpy add a repository and this dependecy.

**Maven**
```xml
<dependencies>
    <dependency>
        <groupId>com.github.Shockbyte</groupId>
        <artifactId>BotBase</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
...
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

**Groovy**
```groovy
repositories {
	maven { url 'https://jitpack.io' }
}
...
dependencies {
	implementation 'com.github.Shockbyte:BotBase:${VERSION}'
}
```