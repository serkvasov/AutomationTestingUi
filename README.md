#Для запуска теста
clean test -DSConfigFile=config/app.properties -DTAGS=@case2

#для запуска в параллель и удаленно переключить параметры (Требуется настройка Selenoid)

run.type = LOCAL
#run.type = REMOTE

run.execute = SINGLE
#run.execute = PARALLEL
