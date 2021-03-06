package com.pau101.fairylights.server.fastener.connection;

import java.util.Objects;

import com.pau101.fairylights.util.Mth;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public final class Segment implements Feature {
	private Vec3d start;

	private Vec3d end;

	private Vec3d rotation;

	private Vec3d vector;

	private double length;

	public Segment(Vec3d start) {
		this.start = Objects.requireNonNull(start, "start");
	}

	public void connectTo(Vec3d end) {
		this.end = Objects.requireNonNull(end, "end");
		length = start.distanceTo(end);
		vector = start.subtract(end).normalize();
		double rotationYaw = -MathHelper.atan2(vector.zCoord, vector.xCoord) - Mth.HALF_PI;
		double rotationPitch = MathHelper.atan2(vector.yCoord, Math.sqrt(vector.xCoord * vector.xCoord + vector.zCoord * vector.zCoord));
		rotation = new Vec3d(rotationYaw, rotationPitch, 0);
	}

	public double getLength() {
		return length;
	}

	public Vec3d getRotation() {
		return rotation;
	}

	public Vec3d getStart() {
		return start;
	}

	public Vec3d getEnd() {
		return end;
	}

	public Vec3d pointAt(double t) {
		return Mth.lerp(start, end, t);
	}

	public Vec3d getVector() {
		return vector;
	}

	@Override
	public int getId() {
		return 0;
	}
}
