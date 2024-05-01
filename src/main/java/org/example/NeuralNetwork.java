package org.example;

import java.util.Arrays;

public class NeuralNetwork {

    private double[][] dense1;
    private double[] bias1;
    private double[][] dense2;
    private double[] bias2;
    private double[][] dense3;
    private double[] bias3;

    public NeuralNetwork() {
	this.dense1 = new double[][] {
	    { 1, 0 },
	    { 0, 1 },
	    { 1, 0 }
	};
	this.bias1 = new double[] {0, 0, 0};
	this.dense2 = new double[][] {
	    {1, 0, 0},
	    {0, 1, 0},
	    {0, 0, 1}
	};
	this.bias2 = new double[] {0, 0, 0};
	this.dense3 = new double[][] {
	    {1, 0, 0},
	    {0, 1, 0}
	};
	this.bias3 = new double[] {0, 0};
    }

    public int selectAction(double x, double y) {
	double[] input = { x, y };
	double[] output = this.forward(input);
	int action = 0;
	for (int i = 1; i < output.length; i++) {
	    if (output[i] > output[action]) {
		action = i;
	    }
	}
	return action;
    }

    private double[] forward(double[] x) {
	double[] x1 = ReLU(plus(matMul(this.dense1, x), this.bias1));
	double[] x2 = ReLU(plus(matMul(this.dense2, x1), this.bias2));
	double[] x3 = plus(matMul(this.dense3, x2), this.bias3);
	return x3;
    }

    private static double[] matMul(double[][] w, double[] x) {
	int n = w.length;
	int m = w[0].length;

	double[] y = new double[n];
	for (int i = 0; i < n; i++) {
	    y[i] = 0.0;
	    for (int j = 0; j < m; j++) {
		y[i] += w[i][j] * x[j];
	    }
	}
	return y;
    }

    private static double[] plus(double[] b, double[] x) {
	double[] y = new double[b.length];
	for (int i = 0; i < b.length; i++) {
	    y[i] = b[i] + x[i];
	}
	return y;
    }

    private static double[] ReLU(double[] x) {
	double[] y = new double[x.length];
	for (int i = 0; i < x.length; i++) {
	    if (x[i] >= 0.0) {
		y[i] = x[i];
	    } else {
		y[i] = 0.0;
	    }
	}
	return y;
    }

    public static void main(String[] args) {
	NeuralNetwork nn = new NeuralNetwork();
	int action = nn.selectAction(1.0, 2.0);
	System.out.println("Selected action: " + action);
    }

};

/*
[('dense1.weight', tensor([[ 7.4408e-01, -1.3984e+00,  3.0730e+00, -2.7294e-01,  2.9824e-01,
	  4.1587e-02],
	[ 8.0831e-02, -4.7823e+00,  1.8955e+00,  2.3672e+00, -2.6499e-01,
	 -6.7457e-01],
	[-4.1926e-01, -1.3697e+00,  1.5276e+00, -5.0751e-02, -1.1176e+00,
	 -1.1852e+00],
	[-2.7305e-02,  3.0028e+00,  5.8801e-01, -9.0011e-01,  3.7567e-01,
	  6.9754e-01],
	[-1.9780e-01,  2.1611e-01,  2.3601e-01, -9.1877e-02, -2.2541e-01,
	 -3.1776e-01],
	[ 1.2422e+00, -2.4823e+00,  1.3550e-01,  1.0852e+00, -1.4347e-01,
	 -4.8052e-01],
	[-6.6967e-02, -1.7403e+00, -5.4492e-01,  4.0490e-01, -5.0224e-02,
	 -3.9883e-01],
	[ 1.6394e-02, -2.5366e-01,  7.1094e-02,  3.6782e-02,  3.0570e-01,
	 -2.0639e-01],
	[-4.8009e-01, -3.5727e+00,  5.1287e-01,  1.3601e+00, -1.1796e-01,
	  6.6688e-01],
	[ 5.7933e-01,  7.2341e+00, -5.6873e-01, -2.2644e-03,  9.0309e-02,
	  8.8614e-02],
	[-4.2772e-02,  1.2354e+00, -1.0412e-01, -1.3092e+00,  3.9875e-01,
	  5.6146e-01],
	[-1.1484e+00,  3.1760e+00, -2.5121e-01, -1.1671e-01,  4.8573e-01,
	  1.1177e+00],
	[-2.2054e-01,  2.9707e+00, -3.7644e-01,  1.1096e-01,  4.6048e-01,
	  5.2523e-01],
	[ 9.2307e-01, -5.0349e-01,  4.2873e-01,  7.6117e-01, -6.1329e-01,
	 -1.2029e+00],
	[-2.0404e+00, -4.3897e+00, -3.7728e-02,  2.9074e+00,  2.6733e-01,
	  3.7996e-01],
	[-1.7193e-01, -1.3628e-01,  5.0616e-01,  1.7630e-01, -1.5325e-01,
	 -2.5032e-01],
	[-1.0857e+00, -4.5135e+00, -6.4446e-01,  6.9061e-01,  1.2892e-01,
	  1.4674e-01],
	[-4.1323e-01, -1.0617e+00,  3.6228e+00,  3.3082e-02, -6.2196e-02,
	 -8.9886e-02],
	[-2.7648e-01,  5.2294e+00, -2.8592e-01, -2.4367e-01,  9.1699e-02,
	 -3.4438e-01],
	[ 2.3771e-02, -3.5938e-01,  8.0260e-01,  1.0571e+00,  1.8915e-01,
	  1.2469e+00],
	[ 4.9246e-01, -5.1308e-01, -9.5084e-01, -1.2030e+00,  2.6384e-01,
	  5.4279e-01],
	[-7.4828e-02, -1.6373e+00,  2.3766e+00,  1.1395e+00, -1.0004e-01,
	 -1.1067e-02],
	[-9.4680e-01, -1.6325e+00, -8.4167e-02,  3.6660e-01,  6.3374e-01,
	  9.3177e-01],
	[ 8.9273e-02, -1.1033e+00,  2.9647e+00,  1.2006e+00,  3.8638e-02,
	 -7.6467e-02],
	[-2.3985e-01, -3.9298e+00,  4.4473e-01,  1.4668e+00,  4.6795e-01,
	  7.9461e-01],
	[ 4.8681e-01, -4.1948e+00, -1.0934e-01,  8.0548e-01, -4.7740e-02,
	  1.1829e-01],
	[ 6.9399e-01,  1.4220e+00, -3.6009e-01, -1.6414e+00,  2.9700e-01,
	  9.9259e-01],
	[ 2.2264e-01, -7.0062e-02,  9.7566e-02,  3.0045e-01,  3.8172e-01,
	  3.1665e-01],
	[-5.1921e-01,  4.5759e+00,  2.2017e-01, -9.7080e-01,  1.7044e-03,
	  5.2705e-02],
	[-3.1762e-02, -3.7102e+00, -3.7345e-01,  1.5338e+00, -1.5579e-01,
	  1.0144e-01],
	[ 2.9348e-01,  3.2950e+00,  8.4471e-01, -1.1208e+00,  3.3462e-01,
	  7.2175e-02],
	[-1.2656e+00, -4.9942e-02, -7.0072e-01, -2.8266e-01,  3.4126e-01,
	  3.4183e-01]])), ('dense1.bias', tensor([-0.0780,  0.0679, -1.2288,  0.4202, -0.3900, -0.2827, -0.0869, -0.3371,
	 0.1223,  0.0935,  0.3646,  0.3930,  0.3894, -0.4279,  0.4923, -0.4637,
	 0.4104, -0.2052, -0.0283,  1.1027,  0.9982, -0.1218,  1.0316,  0.1969,
	 0.7304,  0.2571,  0.2429, -0.2710, -0.2690,  0.1835,  0.7113,  0.6036])), ('dense2.weight', tensor([[ 0.1340, -0.8728,  0.3860,  ..., -1.4117, -0.0122, -0.8165],
	[ 0.1385,  0.6883,  0.5774,  ..., -0.1274, -0.0966,  0.2642],
	[-0.2363, -1.2859, -0.8431,  ...,  0.8103,  1.3214,  0.3546],
	...,
	[ 0.1203,  0.2692,  0.3029,  ..., -0.8468, -0.0576, -1.7080],
	[-0.0307,  0.0553, -0.1630,  ..., -0.1084, -0.1331,  0.0764],
	[ 0.4789,  0.1827,  0.9310,  ..., -1.7448, -0.2074, -0.6286]])), ('dense2.bias', tensor([-3.3994e-02,  2.8151e-02, -2.2356e-01, -1.1119e-01,  1.3706e-01,
	 1.9680e-01,  2.1431e-01,  1.1413e-01,  1.8353e-01, -1.2659e-01,
	 1.2394e-01, -4.1807e-02, -7.2866e-02, -4.6398e-01, -1.9940e-01,
	-2.3865e-01, -2.4625e-02, -1.9180e-03,  1.7656e-01, -7.5577e-02,
	 2.9065e-01, -3.7467e-02,  1.3522e-01,  2.6998e-01,  1.7550e-04,
	-3.1232e-01, -5.1271e-02,  2.0238e-01,  9.7382e-01, -4.1364e-02,
	-2.2848e-02, -3.7646e-01])), ('dense3.weight', tensor([[ 4.9915, -0.4250,  0.6720,  0.0813,  1.5243,  2.0208,  1.0875,  4.2173,
	 -0.5157, -5.7106, -0.5928,  4.2441,  1.6378, -1.3601, -0.5745, -0.7571,
	 -0.4238,  0.0491,  0.8754,  0.0964,  1.7402,  0.4824,  1.0458, 10.1079,
	 -0.6457, -0.2537, -0.8267, -0.0430, -0.9393, -0.2909,  0.1017, -1.1961],
	[ 5.0245, -0.4136,  3.9306, -0.0324,  0.1769,  0.3158,  0.0797,  2.0310,
	 -0.1533, -6.0217, -0.1841,  3.1036,  0.9354, -1.6828, -0.8895, -1.0483,
	 -0.4577, -0.0637,  0.2168,  0.0474,  1.3748, -3.2954,  0.7744,  9.6002,
	 -0.2849, -0.6002, -1.4655, -1.2463, -0.4318,  1.6650, -0.1590, -1.0044]])), ('dense3.bias', tensor([-0.2926,  0.1783]))])
*/
