package mlModel;

import java.util.List;

import excelReader.ExcelReader;
import smile.regression.LinearModel;
import smile.regression.OLS;

public class MLModel {
	

    public static void main(String[] args) throws Exception {
        List<double[]> data = ExcelReader.readExcel("excel/RightToVote.xlsx");

        int trainSize = 75;
        double[][] trainData = new double[trainSize][3];
        double[] trainLabels = new double[trainSize];

        double[][] testData = new double[25][3];
        double[] testLabels = new double[25];

        for (int i = 0; i < data.size(); i++) {
            double[] row = data.get(i);
            if (i < trainSize) {
                System.arraycopy(row, 0, trainData[i], 0, 3);
                trainLabels[i] = row[3];
            } else {
                System.arraycopy(row, 0, testData[i - trainSize], 0, 3);
                testLabels[i - trainSize] = row[3];
            }
        }

        LinearModel model = OLS.fit(trainData, trainLabels);

        for (int i = 0; i < testData.length; i++) {
            double predicted = model.predict(testData[i]);
            System.out.println("Predicted: " + predicted + ", Actual: " + testLabels[i]);
        }
    }

}
