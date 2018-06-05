package com.tank.subweapons;

import com.badlogic.gdx.graphics.Texture;
import com.tank.actor.projectiles.Moose;
import com.tank.actor.vehicles.PlayerTank;
import com.tank.stats.Stats;
import com.tank.utils.Assets;

public class MooseStampedeSubWeapon extends SubWeapon{
	
	private static Texture mooseTexture = Assets.manager.get(Assets.moose_icon);
	
	public MooseStampedeSubWeapon(int ammo) {
		super("Moose Stampede", mooseTexture, ammo);
	}

	@Override
	public void shoot(PlayerTank source) {
		int mooseCount = (source.getStatValue("Max Projectile") * 3) + 8;
		for (int i = 0; i < mooseCount; i++) {
			source.getStage().addActor(
					new Moose(source, createStats(source),source.getPlayer().cursor.getStagePos().x,
							source.getPlayer().cursor.getStagePos().y));
		}
		int fireRate = source.getStatValue("Fire Rate");
		source.setReloadTime(6.0f * (1.0f - ((float) (fireRate) / (fireRate + 60))));
	}
	
	public Stats createStats(PlayerTank source) {
		Stats stats = new Stats();
		stats.addStat("Damage", (int)(source.getStatValue("Damage") * 0.5) + 40);
		stats.addStat("Projectile Speed", (int)(((Math.random() * 50) + 75) * Math.sqrt(source.getStatValue("Projectile Speed"))));
		stats.addStat("Projectile Durability", source.getStatValue("Projectile Durability") * 3 + 1);
		return stats;
	}
}