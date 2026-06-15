# 🔥 TokenSMP – Ultimate Token Plugin for Paper 1.21.4+

**TokenSMP** is a next‑level Minecraft plugin that introduces **10 unique tokens** (7 obtainable by players, 3 admin‑only) with **3 completely custom abilities per token** (no boring potion effects).  
It features a stunning **spin‑on-join animation**, **life system** (5 lives, permanent ban, revive recipe), **bounty system**, and a **full Admin GUI** for one‑click control.

## ✨ Features
- 🎡 Spin Wheel – smooth particle/sound animation on join or `/spin`
- 🎁 7 Normal Tokens – each with 3 unique, visually impressive abilities
- 👑 3 Admin Tokens – never appear in spin, only via admin GUI
- ❤️ Life System – 5 hearts above XP bar, permanent ban at 0, craftable revive item
- 🎯 Bounty System – place bounties on players via admin panel
- 🖥️ Advanced Admin GUI – manage tokens, lives, cooldowns, bounties without commands
- ⏱️ Cooldown System – bossbar/actionbar indicators, custom particles
- 🎨 Fully Custom Abilities – no vanilla effects, only unique mechanics
- 📦 Physical Token Shards – right‑click item to use abilities
- 🧩 Optimised for large SMP servers

## 🎮 Commands
| Command | Description | Permission |
|---------|-------------|-------------|
| `/tokens` | Show your owned tokens | `tokensmp.player` |
| `/tokeninfo` | Show all 10 tokens and abilities | `tokensmp.player` |
| `/spin` | Manually spin the wheel | `tokensmp.player` |
| `/tokensmp give <player> <token>` | Give token | `tokensmp.admin` |
| `/tokensmp remove <player> <token>` | Remove token | `tokensmp.admin` |
| `/tokensmp gui` | Open admin panel | `tokensmp.admin` |
| `/tokensmp reload` | Reload config | `tokensmp.admin` |

## 🛠️ Installation
1. Download `TokenSMP-1.0.0.jar` from [Releases](../../releases)
2. Place in `plugins/` folder
3. Restart server
4. Configure `config.yml` and `messages.yml`

## 🧪 Compiling from Source
```bash
git clone https://github.com/your-username/TokenSMP.git
cd TokenSMP
mvn clean package
