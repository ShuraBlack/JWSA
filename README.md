[Java]: https://img.shields.io/badge/Java%2011-rgb(235%2C%20149%2C%2042)?style=for-the-badge
[API]: https://img.shields.io/badge/API-Wrapper-blue?style=for-the-badge
[License]: https://img.shields.io/badge/License-MIT-white?style=for-the-badge
[Version]: https://img.shields.io/maven-central/v/io.github.shurablack/JWSA?strategy=highestVersion&style=for-the-badge&color=green
[Discord]: https://img.shields.io/badge/Discord-shurablack-rgb(2%2C%20187%2C%20249)?style=for-the-badge&logo=discord&logoColor=rgb(2%2C%20187%2C%20249)

# 🚀 JWSA (Java Warframe Stat API)

<img align="right" src="https://github.com/user-attachments/assets/e93c8e76-d636-470f-9b81-0e752c7b67e3" height="150" width="150">

[![Java][]][Java]
[![API][]][API]
[![License][]][License]
[![Version][]][Version]
[![Discord][]][Discord]

**JWSA** is a Java-based API wrapper designed to interact with the [Warframe Worldstate API](https://docs.warframestat.us/). It provides an elegant, object-oriented interface to access live game data such as alerts, missions, events, and much more.

## ✨ Features

- 🔄 Fetch real-time data from the Warframe Worldstate API or the static Endpoints
- 🧱 Object-oriented representation of Warframe’s game data
- ⚡ Easy-to-use `request()` and `request(<query>) / requestAll(<query>)` methods to retrieve data *(Caches requests to reduce calls)*
  - Be responsible with your API usage. The endpoints do not have a rate limit
- 💾 Save and load data from local files for offline access *(Java Serializable and Json)*
- 🔍 Support for various game features like alerts, sorties, fissures, syndicates, and more

## 🔧 Installation

This libary is available on maven central. The latest version is always shown in the [GitHub Release](https://github.com/ShuraBlack/JWSA/releases).<br>
The minimum java version supported is **Java 11**.
### Gradle
```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation 'io.github.shurablack:JWSA:$version'
}
```

### Maven
```xml
<dependency>
    <groupId>io.github.shurablack</groupId>
    <artifactId>JWSA</artifactId>
    <version>$version</version>
</dependency>
```

## 🧪 Usage

### Example: Fetching Alerts (Lists)

```java
import de.shurablack.jwsa.api.entities.worldstate.global.Alert;
import de.shurablack.jwsa.api.utils.Logging;
import org.apache.logging.log4j.Level;

import java.util.List;

public class Main {
   public static void main(String[] args) {
      // [OPTIONAL] Enable more logging
      Logging.setLevelTo(Level.ALL);

      List<Alert> alerts = Alert.request();
      for (Alert alert : alerts) {
         System.out.println("Alert Node: " + alert.getMission().getNode());
         System.out.println("Reward: " + alert.getMission().getReward().getAsString());
      }
   }
}
```

### Example: Fetching Sorties (Direct Object)

```java
import de.shurablack.jwsa.api.entities.worldstate.global.Sortie;
import de.shurablack.jwsa.api.utils.Persistence;

public class Main {
   public static void main(String[] args) {
      Sortie sortie = Sortie.request();
      System.out.println("Sortie Boss: " + sortie.getBoss());
      System.out.println("Faction: " + sortie.getFaction().getName());

      // Save sortie data to a JSON file
      Persistence.jsonMapToFile(sortie, "sortie.json");
      
      // Load sortie data from a JSON file
      Sortie loadedSortie = Sortie.deserialize(Persistence.readJsonFromFile("sortie.json"));
   }
}
```

## 📚 Key Classes

These classes come with built-in `request()` methods to fetch specific types of data:

### 🌐 Global Worldstate
- `News` — Latest Warframe news
- `WorldstateTimestamp` — Timestamp of the current Worldstate
- `Alert` — Active alerts
- `Arbitration` — Arbitration state
- `Archon` — Archon hunt data
- `ConstructionProgress` — Construction progress of in-game events
- `Event` — Active events
- `Fissure` — Void fissures
- `GlobalUpgrades` — Global upgrades in the worldstate
- `Invasion` — Active invasions
- `Kuva` — Current Kuva missions
- `Nightwave` — Nightwave challenges
- `Sortie` — Current sortie
- `SteelPath` — Steel Path status
- `Syndicate` — Syndicate missions

### 🪐 Planetary Data
- `CambionStatus` — Fass/Vome cycle on Cambion Drift
- `CetusStatus` — Cetus day/night cycle
- `DeepArchimedea` — Deep Archimedea data
- `EarthCycle` — Earth’s day/night cycle
- `OrbVallisStatus` — Warm/cold cycle of Orb Vallis

### 🚉 Relay & Trader Info
- `ConclaveChallenge` — Conclave challenges
- `DarvoDeal` — Daily deal from Darvo
- `FlashSale` — Flash sales
- `SanctuaryTarget` — Sanctuary target data
- `VaultTrader` — Vault trader state
- `VoidTrader` — Baro Ki'Teer details

### 🪛 Items & mods [STATIC]
- `Item` — General item data
- `Mod` — Mod data

### 🔫 Weapon [STATIC]
- `Weapon` — Detailed weapon information

### 🤖 Warframe [STATIC]
- `Warframe` — Detailed Warframe information

## 📦 Dependencies

- [Lombok](https://projectlombok.org/) — Reduce boilerplate Java code.
- [JSON](https://github.com/stleary/JSON-java) — Parse JSON responses.
- [Log4j2](https://logging.apache.org/log4j/2.x/) — Powerful logging.
- [SLF4J](http://www.slf4j.org/) — Logging abstraction.

## 🤝 Contributing

Contributions are welcome! 🙌  
Feel free to open issues, request features, or submit pull requests to improve the project.
> You can also add me on discord and chat with me if you need help ([shurablack](https://discord.com/users/286628057551208450))

## 📜 License

This project is licensed under the **MIT License**.  
See the `LICENSE` file for more details.

## 🙏 Acknowledgments

- Thanks to the creators of the [Warframe Worldstate API](https://docs.warframestat.us/).
- And to the amazing Warframe community for their continued inspiration ❤️.
