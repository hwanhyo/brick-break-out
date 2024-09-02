# 벽돌깨기 게임 🧱

Java로 개발한 초간단 벽돌깨기 게임입니다. <br>
조작은 마우스로 가능합니다.

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
플레이어는 시작 시 난이도와 맵을 순서대로 선택하여 게임을 시작합니다.
<img width="606" alt="image" src="https://github.com/user-attachments/assets/34d30168-14be-4c3d-acc4-041482d966d7">
   <img width="639" alt="image" src="https://github.com/user-attachments/assets/834df7c0-9d60-49cd-9a1a-35e61a21a482">

### 2. **게임 플레이 화면**
플레이어는 마우스를 사용하여 바를 좌우로 이동시키고, 공을 튕겨 벽돌을 깨트립니다.
   <img width="551" alt="image" src="https://github.com/user-attachments/assets/ef659e79-b9ad-403d-97ee-19d387c19bfd">

### 3. **게임 종료 화면**
모든 벽돌이 깨지거나 공이 바닥으로 떨어지면 게임이 종료됩니다. 플레이어는 게임 재시작 버튼을 눌러 재시작할 수 있습니다.
   <img width="855" alt="image" src="https://github.com/user-attachments/assets/dc37e78f-7d94-4f9c-8c3d-67b6cc5b1b29">
<img width="891" alt="image" src="https://github.com/user-attachments/assets/f7555019-5aa8-40fd-9266-8692cdc646cd">

## 설치 및 실행 방법 🎮

## 설치 및 실행 방법

1. **배포 파일 다운로드**:
   - [src/releases/v2.zip](src/releases/v2.zip)를 클릭하여 배포 파일을 다운로드합니다.

2. **압축 풀기**:
   - 다운로드한 `v2.zip` 파일을 원하는 디렉토리에 압축을 풉니다.

3. **애플리케이션 실행**:
   - Mac: 압축을 푼 폴더에서 `break-out_macos_0_1_0.dmg`을 더블 클릭하여 설치 후 `Applications/brick-break-out-v2` 경로에 설치된 응용프로그램(`brick-break-out-v2`)을 실행합니다.
   - Windows: `break-out_windows-x64_0_1_0.exe`를 더블 클릭하여 설치 후 ` C:\Program Files` 경로에 설치된 응용프로그램(`brick-break-out-v2`)을 실행합니다.

또는 프로젝트 clone 후 IDE에서 Main 클래스를 실행합니다.

## 주요 기능
- 난이도 선택 기능
   - EASY: 이동 속도가 일정한 공 2개
   - HARD: 이동 속도가 점점 증가하는 공 4개
   - EXTREME: 이동 속도가 빠르게 증가하는 공 8개
- 맵 선택 기능
- 배경 음악 및 효과음: 게임을 실행하면 배경 음악이 반복 재생됩니다. 공이 벽돌에 부딪힐 때, 벽돌이 깨질 때 효과음이 재생됩니다.
- 공과 벽돌의 충돌 처리: 공이 벽돌에 부딪히면 벽돌이 깨지고, 공이 튕겨집니다.

## 이슈 및 해결 과정 🚨
[리포지토리 이슈](https://github.com/caboooom/brick-break-out/issues)