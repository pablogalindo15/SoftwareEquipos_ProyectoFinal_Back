call java -jar Cascara.jar
call mvn -N io.takari:maven:0.7.7:wrapper
start /b "" cmd /c del Cascara.jar /s /f /q
call git remote rm origin
call git remote add origin git@github.com:Uniandes-isis2603/prueba.git
start /b "" cmd /c del "%~f0"&exit /b
