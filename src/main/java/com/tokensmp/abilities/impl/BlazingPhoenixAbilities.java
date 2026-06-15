private static Ability firestorm() {
    return new Ability("Firestorm",
        "Create a tornado of fire around you, damaging and pushing enemies.",
        45,
        player -> {
            World w = player.getWorld();
            Location loc = player.getLocation();
            w.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1f, 0.7f);
            for (double r = 0; r <= 2 * Math.PI; r += Math.PI / 8) {
                double x = Math.cos(r) * 3;
                double z = Math.sin(r) * 3;
                Location particleLoc = loc.clone().add(x, 1, z);
                w.spawnParticle(Particle.FLAME, particleLoc, 15, 0.3, 0.3, 0.3, 0);
                w.spawnParticle(Particle.LARGE_SMOKE, particleLoc, 5, 0.2, 0.2, 0.2, 0);
            }
            player.getNearbyEntities(4, 3, 4).forEach(e -> {
                if (e instanceof Player target && target != player) {
                    target.damage(9, player);
                    Vector away = target.getLocation().toVector().subtract(player.getLocation().toVector()).normalize().multiply(1.2);
                    target.setVelocity(away);
                    target.setFireTicks(100);
                }
            });
        });
}
