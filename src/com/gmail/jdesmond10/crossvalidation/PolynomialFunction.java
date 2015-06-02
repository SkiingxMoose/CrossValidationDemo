package com.gmail.jdesmond10.crossvalidation;

import java.util.function.Function;

/**
 * A function of the type Y=B1 + B2x + B3x^2 + B4x^3 + B5x^4
 * 
 * Use the fields betaOne through betaFive to get the values of the polynomials.
 * Use the field complexity to get a measure of the polynomials complexity. It's
 * a weighted sum of the coefficients.
 * 
 * @author Josh Desmond
 *
 */
public final class PolynomialFunction implements Function<Float, Float> {

	/** The intercept. */
	public final float betaOne;
	public final float betaTwo;
	public final float betaThree;
	public final float betaFour;
	public final float betaFive;
	public final float complexity;

	public PolynomialFunction(final float b1, final float b2, final float b3,
			final float b4, final float b5) {
		this.betaOne = b1;
		this.betaTwo = b2;
		this.betaThree = b3;
		this.betaFour = b4;
		this.betaFive = b5;
		this.complexity = determineComplexity();
	}

	@Override
	public Float apply(final Float t) {
		float toRet = betaOne;
		toRet += betaTwo * t;
		toRet += betaThree * t * t;
		toRet += betaFour * t * t * t;
		toRet += betaFive * t * t * t * t;
		return toRet;
	}

	/**
	 * Returns a weighted sum of the coefficients.
	 * 
	 */
	private float determineComplexity() {
		float sum = 0;
		sum += .25 * betaTwo;
		sum += .5 * betaThree;
		sum += betaFour;
		sum += 2 * betaFive;

		return sum;
	}

	@Override
	public String toString() {
		return String.format(
				"Y = %s + %sx + %sx^2 + %sx^3 + %sx^4 with complexity = %s",
				betaOne, betaTwo, betaThree, betaFour, betaFive, complexity);
	}

}