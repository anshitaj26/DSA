class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if (originalColor != color) {
            dfs(image, sr, sc, originalColor, color);
        }
        return image;
    }
    
    private void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        // Check boundary conditions and if current pixel matches the original color
        if (r < 0 || c < 0 || r >= image.length || c >= image[0].length || image[r][c] != originalColor) {
            return;
        }
        
        // Change the color
        image[r][c] = newColor;
        
        // Recursively call for 4-directionally adjacent pixels
        dfs(image, r - 1, c, originalColor, newColor); // Up
        dfs(image, r + 1, c, originalColor, newColor); // Down
        dfs(image, r, c - 1, originalColor, newColor); // Left
        dfs(image, r, c + 1, originalColor, newColor); // Right
    }
}