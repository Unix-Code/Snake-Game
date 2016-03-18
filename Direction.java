public enum Direction {
    Up(0), Down(1), Left(2), Right(3), Nothing(4);
    
    protected int move;
    protected boolean vertical;
    protected boolean horizontal;
    
    Direction(int direction) {
        vertical = false;
        horizontal = false;
        
        switch (direction) {
            case 0 :
            move = -1;
            vertical = true;
            break;
            
            case 1 :
            move = 1;
            vertical = true;
            break;
            
            case 2 :
            move = -1;
            horizontal = true;
            break;
            
            case 3 :
            move = 1;
            horizontal = true;
            break;
            
            case 4 :
            move = 0;
            break;
        }
    }
}
