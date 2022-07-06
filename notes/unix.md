# Unix Notes

## tr
```bash
head -n 1 ~/samples/data/top100.csv | tr ',' '\n'

head -n 1 ~/samples/data/top100.csv | tr ',' '\n' | tr -d '"'

echo "Welcome to the shell" | tr 'shell' 'machine'
```

## cut
```bash
cut -d',' -f 3 ~/samples/data/top100.csv | head

cut -d',' -f 1,3 ~/samples/data/top100.csv | head

tail -n 5 ~/samples/logs/web-server-logs.txt | cut -c 12-19

ps -e | cut -c 26-500
```

## sed
```bash
cut -d',' -f 3 ~/samples/data/top100.csv | head

cut -d',' -f 1,3 ~/samples/data/top100.csv | head

tail -n 5 ~/samples/logs/web-server-logs.txt | cut -c 12-19

ps -e | cut -c 26-500
```
