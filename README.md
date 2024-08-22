# 벽돌깨기 게임 🧱

Java로 개발한 간단한 벽돌깨기 게임입니다. <br>
플레이어는 마우스로 바를 조작하여 공을 튕깁니다. <br>
화면 상단의 벽돌을 모두 깨트리는 것이 목표입니다.

## 💻 개발 기간 및 개발 환경
- 개발 기간: 2024.08.20 ~ 2024.08.22
- **개발 언어**: Java
- **개발 도구**: IntelliJ IDEA
- **빌드 도구**: Maven

## 📚 사용 라이브러리 목록
### 내장 라이브러리
- **javax.swing**: Java의 기본 GUI 구성 요소를 사용하여 게임 화면을 구성
- **javax.sound.sampled**: 게임 내에서 효과음 및 배경 음악을 재생하기 위해 사용
- **org.apache.logging.log4j**: 로깅을 통해 디버깅 및 개발 중 발생한 이슈를 추적하기 위해 사용


## 🕹️ 게임 화면

게임 플레이 영상 링크: (추가 예정) 

### 1. **메인 화면**
플레이어는 시작 시 여러 맵 중 하나를 선택하여 게임을 시작합니다.
   <img width="639" alt="image" src="https://github.com/user-attachments/assets/834df7c0-9d60-49cd-9a1a-35e61a21a482">

### 2. **게임 플레이 화면**
플레이어는 마우스를 사용하여 바를 좌우로 이동시키고, 공을 튕겨 벽돌을 깨트립니다.
   <img width="551" alt="image" src="https://github.com/user-attachments/assets/ef659e79-b9ad-403d-97ee-19d387c19bfd">

### 3. **게임 종료 화면**
모든 벽돌이 깨지거나 공이 바닥으로 떨어지면 게임이 종료됩니다. 플레이어는 게임 재시작 버튼을 눌러 재시작할 수 있습니다.
   <img width="855" alt="image" src="https://github.com/user-attachments/assets/dc37e78f-7d94-4f9c-8c3d-67b6cc5b1b29">
<img width="891" alt="image" src="https://github.com/user-attachments/assets/f7555019-5aa8-40fd-9266-8692cdc646cd">

## 설치 및 실행 방법 🎮
1. **프로젝트 클론**
   ```bash
   git clone https://github.com/caboooom/brick-break-out.git
   ```

2. Maven 의존성 설치
   ```bash
   mvn clean install
   ```

3. 게임 실행
   ```bash
   mvn exec:java -Dexec.mainClass="com.caboooom.Main"
   ```
    또는 IDE에서 Main 클래스를 실행합니다.

## 주요 기능
- 맵 선택 기능: 게임 시작 전에 다양한 맵을 선택할 수 있습니다. 각 맵은 벽돌의 배치가 다릅니다.
- 배경 음악 및 효과음: 게임 내에서 배경 음악이 무작위로 재생되며, 공이 벽돌에 부딪힐 때 효과음이 재생됩니다.
- 공과 벽돌의 충돌 처리: 공이 벽돌에 부딪히면 벽돌이 깨지고, 공이 반사됩니다.

## 이슈 및 해결 과정
