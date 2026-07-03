package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

/** This class handles lists
 *
 */
public class AllListImpl implements AllList {

	private static List<Pair<JButton, Integer>> routeButton = new ArrayList<>();	
	private static List<Pair<JButton, Boolean>> listBasePlayerBlue = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listBasePlayerOrange = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listBasePlayerPurple = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listBasePlayerRed = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listBasePlayerYellow = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listBasePlayerGreen = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listArrivedPlayerBlue = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listArrivedPlayerOrange = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listArrivedPlayerPurple = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listArrivedPlayerRed = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listArrivedPlayerYellow = new ArrayList<>();
	private static List<Pair<JButton, Boolean>> listArrivedPlayerGreen = new ArrayList<>();
	
	public AllListImpl() {}
	
	public List<Pair<JButton, Boolean>> getListBasePlayerYellow() {
		return listBasePlayerYellow;
	}

	public void setListBasePlayerYellow(List<Pair<JButton, Boolean>> listBasePlayerYellow) {
		AllListImpl.listBasePlayerYellow = listBasePlayerYellow;
	}

	public List<Pair<JButton, Boolean>> getListBasePlayerGreen() {
		return listBasePlayerGreen;
	}

	public void setListBasePlayerGreen(List<Pair<JButton, Boolean>> listBasePlayerGreen) {
		AllListImpl.listBasePlayerGreen = listBasePlayerGreen;
	}

	public List<Pair<JButton, Boolean>> getListArrivedPlayerYellow() {
		return listArrivedPlayerYellow;
	}

	public void setListArrivedPlayerYellow(List<Pair<JButton, Boolean>> listArrivedPlayerYellow) {
		AllListImpl.listArrivedPlayerYellow = listArrivedPlayerYellow;
	}

	public List<Pair<JButton, Boolean>> getListArrivedPlayerGreen() {
		return listArrivedPlayerGreen;
	}

	public void setListArrivedPlayerGreen(List<Pair<JButton, Boolean>> listArrivedPlayerGreen) {
		AllListImpl.listArrivedPlayerGreen = listArrivedPlayerGreen;
	}
	
	public List<Pair<JButton,Integer>> getRouteButton() {
		return routeButton;
	}

	public void setRouteButton(List<Pair<JButton,Integer>> routeButton) {
		AllListImpl.routeButton = routeButton;
	}
	
	public List<Pair<JButton, Boolean>> getListBasePlayerBlue() {
		return listBasePlayerBlue;
	}

	public void setListBasePlayerBlue(List<Pair<JButton, Boolean>> listBasePlayerBlue) {
		AllListImpl.listBasePlayerBlue = listBasePlayerBlue;
	}

	public List<Pair<JButton, Boolean>> getListBasePlayerOrange() {
		return listBasePlayerOrange;
	}

	public void setListBasePlayerOrange(List<Pair<JButton, Boolean>> listBasePlayerOrange) {
		AllListImpl.listBasePlayerOrange = listBasePlayerOrange;
	}

	public List<Pair<JButton, Boolean>> getListBasePlayerPurple() {
		return listBasePlayerPurple;
	}

	public void setListBasePlayerPurple(List<Pair<JButton, Boolean>> listBasePlayerPurple) {
		AllListImpl.listBasePlayerPurple = listBasePlayerPurple;
	}
	
	public List<Pair<JButton, Boolean>> getListBasePlayerRed() {
		return listBasePlayerRed;
	}

	public void setListBasePlayerRed(List<Pair<JButton, Boolean>> listBasePlayerRed) {
		AllListImpl.listBasePlayerRed = listBasePlayerRed;
	}

	public List<Pair<JButton, Boolean>> getListArrivedPlayerBlue() {
		return listArrivedPlayerBlue;
	}

	public void setListArrivedPlayerBlue(List<Pair<JButton, Boolean>> listArrivedPlayerBlue) {
		AllListImpl.listArrivedPlayerBlue = listArrivedPlayerBlue;
	}

	public List<Pair<JButton, Boolean>> getListArrivedPlayerOrange() {
		return listArrivedPlayerOrange;
	}

	public void setListArrivedPlayerOrange(List<Pair<JButton, Boolean>> listArrivedPlayerOrange) {
		AllListImpl.listArrivedPlayerOrange = listArrivedPlayerOrange;
	}

	public List<Pair<JButton, Boolean>> getListArrivedPlayerPurple() {
		return listArrivedPlayerPurple;
	}

	public void setListArrivedPlayerPurple(List<Pair<JButton, Boolean>> listArrivedPlayerPurple) {
		AllListImpl.listArrivedPlayerPurple = listArrivedPlayerPurple;
	}

	public List<Pair<JButton, Boolean>> getListArrivedPlayerRed() {
		return listArrivedPlayerRed;
	}

	public void setListArrivedPlayerRed(List<Pair<JButton, Boolean>> listArrivedPlayerRed) {
		AllListImpl.listArrivedPlayerRed = listArrivedPlayerRed;
	}	
}
