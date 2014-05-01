
/**
 *
 * @author sheff
 */
public class Exchange {
    
    private double[][] chart;
    private boolean calcd = false;

    public Exchange(int n) {
        chart = new double[n][n];
        for (int i = 0; i < chart.length; i++) {
            chart[i][i] = 1;
        }
    }

    public void setRate(int from, int to, double rate) {
        chart[from][to] = rate;
    }

    public boolean arbitrageExists() {
        for (int i = 0; i < chart.length; i++) {
            if (bestExchangeRate(i, i) > 1) return true;
        }
        
        return false;
    }

    public double bestExchangeRate(int from, int to) {
        if (calcd) return chart[from][to];
        
        for (int k = 0; k < chart.length; k++)
        {
            for (int i = 0; i < chart.length; i++)
            {
                for (int j = 0; j < chart.length; j++)
                {
                    double temp = chart[i][k] * chart[k][j];
                    if (chart[i][j] < temp)
                    {
                        chart[i][j] = temp;
                    }
                }
             }
        }
        
        calcd = true;
        return chart[from][to];
    }
    
}
