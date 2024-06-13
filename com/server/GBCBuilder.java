/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 *
 * @author ADMIN
 */
public class GBCBuilder extends GridBagConstraints{
	private static final long serialVersionUID = 1L;

	public GBCBuilder(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}

	public GBCBuilder setGrid(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
		return this;
	}

	public GBCBuilder setSpan(int gridwidth, int gridheight) {
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
		return this;
	}

	public GBCBuilder setFill(int fill) {
		this.fill = fill;
		return this;
	}

	public GBCBuilder setWeight(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}

	public GBCBuilder setInsets(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}

	public GBCBuilder setInsets(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	
	public GBCBuilder setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}
}
