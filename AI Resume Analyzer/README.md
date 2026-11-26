# AI Resume Analyzer — Java Swing Frontend

A UI-only Java Swing frontend for an "AI Resume Analyzer" MCA JDBC assignment.
No JDBC, file parsing, or AI logic is implemented — the UI is structured and
commented so those layers can be added later.

## How to compile & run

Requires a JDK (8+) with `javac` on your PATH.

```bash
# From this folder:
javac -d out $(find src -name "*.java")
java -cp out com.resumeanalyzer.Main
```

On Windows (PowerShell), instead of `find`, compile with:
```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })
java -cp out com.resumeanalyzer.Main
```

Or simply open the `src` folder as a Java project in IntelliJ IDEA /
Eclipse / NetBeans, mark `src` as the source root, and run `Main.java`.

## Project structure

```
com.resumeanalyzer
  Main.java                  - application entry point

com.resumeanalyzer.ui
  DashboardFrame.java         - main JFrame (header + sidebar + CardLayout content)
  HeaderPanel.java            - top header (logo, title, date, profile icon)
  SidebarPanel.java           - left navigation menu
  DashboardPanel.java         - dashboard stat cards + recent activity
  UploadPanel.java            - candidate form + drag & drop upload area
  AnalysisPanel.java          - progress metrics, skill tags, summary, recommendations
  CandidatePanel.java         - candidate profile card
  PlaceholderPanel.java       - reusable "coming soon" screen (Skills Match, Reports, Settings, Help)

com.resumeanalyzer.components
  RoundedButton.java           - gradient rounded button (primary/secondary/danger styles)
  RoundedTextField.java        - rounded text field with placeholder + focus highlight
  RoundedPanel.java            - white rounded "card" container with soft shadow
  DashboardCard.java           - stat card (icon, big number, accent strip, hover lift)
  ProgressCard.java            - labeled metric with custom-painted progress bar
  SkillTag.java                - rounded skill "pill" label
```

## Ready for JDBC integration

Every panel that would eventually talk to a database is commented with where
that logic belongs, e.g.:

- `UploadPanel`'s **Save** button → `com.resumeanalyzer.dao.CandidateDAO`
- `CandidatePanel` fields → populated from a `Candidate` model/DAO query
- `AnalysisPanel` progress values → populated from analysis results
- Validation labels in `UploadPanel` → shown/hidden by a future validation layer

Suggested future packages: `com.resumeanalyzer.dao`, `com.resumeanalyzer.model`,
`com.resumeanalyzer.service`, `com.resumeanalyzer.exception`.
