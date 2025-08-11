package dev.syntax.model;

public class Print {
	private Print() {}

	static public void startPrint(int time, int temp) { 	        
	System.out.println("음식을 데우는 중입니다... 🔥");
    System.out.println("┌──────────────────────────────────┐");
    System.out.println("│  ┌───────────────┐ ┌───────────┐ │");
    System.out.println("│  │   .---.       │ │   " + time + " 초    │ │");
    System.out.println("│  │  (     )      │ │ 1  2  3   │ │");
    System.out.println("│  │   '---'       │ │ 4  5  6   │ │");
    System.out.println("│  │               │ │ 7  8  9   │ │");
    System.out.println("│  │   🔥🔥🔥  "+ temp +" 도     ▶️ 0  ⏹️  │ │");
    System.out.println("│  └───────────────┘ └───────────┘ │");
    System.out.println("└──────────────────────────────────┘");
	}

	static public void pausePrint(int time, int temp) { 	        
	System.out.println("❌❌전자레인지 일시 정지❌❌");
    System.out.println("┌──────────────────────────────────┐");
    System.out.println("│  ┌───────────────┐ ┌───────────┐ │");
    System.out.println("│  │   .---.       │ │   " + time + " 초    │ │");
    System.out.println("│  │  (     )      │ │ 1  2  3   │ │");
    System.out.println("│  │   '---'       │ │ 4  5  6   │ │");
    System.out.println("│  │               │ │ 7  8  9   │ │");
    System.out.println("│  │   🔥🔥🔥  "+ temp +" 도     ▶️ 0  ⏹️  │ │");
    System.out.println("│  └───────────────┘ └───────────┘ │");
    System.out.println("└──────────────────────────────────┘");
	}
	
	static public void endPrint() {
		System.out.println("조리가 끝났습니다! 😋");
        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│  ┌───────────────┐ ┌───────────┐ │");
        System.out.println("│  │               │ │   완료!    │ │");
        System.out.println("│  │               │ │ 1  2  3   │ │");
        System.out.println("│  │    ( )        │ │ 4  5  6   │ │");
        System.out.println("│  │               │ │ 7  8  9   │ │");
        System.out.println("│  │   ✨😋✨           ▶️ 0  ⏹️  │ │");
        System.out.println("│  └───────────────┘ └───────────┘ │");
        System.out.println("└──────────────────────────────────┘");
	}
}
