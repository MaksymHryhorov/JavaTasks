package com.knubisoft.tasks.algorithm.pascaltriangle;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleImpl implements PascalsTriangle {

    @Override
    public List<Integer> calculateLineOfPascalsTriangle(int lineNumber) {
        List<Integer> triangleList = new ArrayList<>();

        if (lineNumber == 0) {
            return triangleList;
        }

        if (lineNumber == 1) {
            triangleList.add(1);
            return triangleList;
        }

        triangleList.add(1);
        List<Integer> prev = calculateLineOfPascalsTriangle(lineNumber - 1);

        for (int i = 1; i < prev.size(); i++) {
            int curr = prev.get(i - 1) + prev.get(i);
            triangleList.add(curr);
        }

        triangleList.add(1);

        return triangleList;
    }
}
