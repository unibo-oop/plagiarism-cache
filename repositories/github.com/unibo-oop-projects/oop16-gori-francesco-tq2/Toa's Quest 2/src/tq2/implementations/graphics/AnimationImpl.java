package tq2.implementations.graphics;

//import java.awt.image.BufferedImage;
import java.util.LinkedList;

import tq2.interfaces.Animation;

public class AnimationImpl implements Animation {
	
	//public Integer frameNum;

	public FrameSequence framesL;	
	public FrameSequence framesR;


	
	public AnimationImpl (LinkedList<Frame> frames, Integer speed, Integer loop) {

		this.framesR = new FrameSequence (frames, speed, loop);
		this.framesL = null;
	}
	
//	public Animation (LinkedList<Sprite> framesL, LinkedList<Sprite> framesR, Integer speed, Integer loop) {
//		this.framesL = new FrameSequence (framesL, speed, loop);
//		this.framesR = new FrameSequence (framesR, speed, loop);
//	}
	
	public AnimationImpl (FrameSequence frames) {

		this.framesR = frames;
		this.framesL = null;
	}
	
	public AnimationImpl (Frame frame) {
		
		LinkedList <Frame> list = new LinkedList<Frame> ();
		list.add(frame);
		
		this.framesR = new FrameSequence (list, 60, 0);
		this.framesL = null;
	}
	
//	public Animation (BufferedImage image) {
//		this(new Frame(image));
//	}
	
	public AnimationImpl (FrameSequence framesL, FrameSequence framesR) {

		this.framesR = framesR;
		this.framesL = framesL;
	}
	
	@Override
	public FrameSequence getFrameSequence(Integer facing) {
		
		FrameSequence frames = this.framesR;
		
		if (!this.isSymmetrical()) {
			if (facing == -1) {
				frames = this.framesL;
			}
		}
		
		return frames;
	}
	
	@Override
	public Frame getFrame(Integer index, Integer facing) {
		return this.getFrameSequence(facing).get(index);
	}
	
	@Override
	public Integer size(Integer facing) {
		return this.getFrameSequence(facing).size();
		
	}
	
	@Override
	public Integer getSpeed(Integer facing) {
		return this.getFrameSequence(facing).getSpeed();
	}
	
	@Override
	public Integer getWidth(Integer facing) {
		return this.getFrameSequence(facing).getWidth();
	}
	
	@Override
	public Integer getHeight(Integer facing) {
		return this.getFrameSequence(facing).getHeight();
	}
	
	@Override
	public Integer getLoop(Integer facing) {
		return this.getFrameSequence(facing).getLoop();
	}
	
	@Override
	public Boolean isSymmetrical() {
		return (this.framesL == null);
	}
	
//	@Override
//	public void setSpeed(Integer speed, Integer facing) {
//		this.getFrameSequence(facing).setSpeed(speed);
//	}
//	
//	@Override
//	public void setSpeed(Integer speed) {
//		this.getFrameSequence(1).setSpeed(speed);
//		this.getFrameSequence(-1).setSpeed(speed);
//	}
//	
//	@Override
//	public void resetSpeed(Integer facing) {
//		this.getFrameSequence(facing).resetSpeed();
//	}
//	
//	@Override
//	public void resetSpeed() {
//		this.getFrameSequence(1).resetSpeed();
//		this.getFrameSequence(-1).resetSpeed();
//	}
}


