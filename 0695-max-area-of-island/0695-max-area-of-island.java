class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
        }
        return maxArea;
    }
    
    private int dfs(int[][] grid, int r, int c) {
        // Check bounds and if current cell is water
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }
        
        // Mark the current cell as visited by changing 1 to 0
        grid[r][c] = 0;
        
        // Count this cell plus all 4-directional neighboring land cells
        return 1 + dfs(grid, r + 1, c) 
                 + dfs(grid, r - 1, c) 
                 + dfs(grid, r, c + 1) 
                 + dfs(grid, r, c - 1);
    }
}