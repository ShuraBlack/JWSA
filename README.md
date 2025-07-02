[Java]: https://img.shields.io/badge/Java-rgb(235%2C%20149%2C%2042)?style=for-the-badge
[API]: https://img.shields.io/badge/API-Documentation-blue?style=for-the-badge&link=https%3A%2F%2Fdocs.warframestat.us%2F
[License]: https://img.shields.io/badge/License-MIT-white?style=for-the-badge
[Version]: https://img.shields.io/badge/Version-1.0.0-green?style=for-the-badge

<img align="right" style="margin-top: 15px" src="" height="150" width="150">

# 🚀 JWSA (Java Warframe Stat API)

[![Java][]][Java]
[![API][]][API]
[![License][]][License]
[![Version][]][Version]

**JWSA** is a Java-based API wrapper designed to interact with the [Warframe Worldstate API](https://docs.warframestat.us/). It provides an elegant, object-oriented interface to access live game data such as alerts, missions, events, and much more.

---

## ✨ Features

- 🔄 Fetch real-time data from the Warframe Worldstate API.
- 🧱 Object-oriented representation of Warframe’s game data.
- ⚡ Easy-to-use `request()` methods to retrieve data. (Caches request to reduce calls)
- 🔍 Support for various game features like alerts, sorties, fissures, syndicates, and more.

---

## 🔧 Installation

1. 📥 Clone the repository:
   ```bash
   git clone https://github.com/ShuraBlack/JWSA.git
   cd JWSA
   ```

2. 🛠️ Build the project with Maven:
   ```bash
   mvn clean install
   ```

3. 📦 Add the generated JAR to your project  
   _(or use the prebuilt release .jar / include the github release)_

---

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

public class Main {
    public static void main(String[] args) {
        Sortie sortie = Sortie.request();
        System.out.println("Sortie Boss: " + sortie.getBoss());
        System.out.println("Faction: " + sortie.getFaction().getName());
    }
}
```

---

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

---

## 📦 Dependencies

- [Lombok](https://projectlombok.org/) — Reduce boilerplate Java code.
- [JSON](https://github.com/stleary/JSON-java) — Parse JSON responses.
- [Log4j2](https://logging.apache.org/log4j/2.x/) — Powerful logging.
- [SLF4J](http://www.slf4j.org/) — Logging abstraction.

---

## 🤝 Contributing

Contributions are welcome! 🙌  
Feel free to open issues, request features, or submit pull requests to improve the project.

---

## 📜 License

This project is licensed under the **MIT License**.  
See the `LICENSE` file for more details.

---

## 🙏 Acknowledgments

- Thanks to the creators of the [Warframe Worldstate API](https://docs.warframestat.us/).
- And to the amazing Warframe community for their continued inspiration ❤️.