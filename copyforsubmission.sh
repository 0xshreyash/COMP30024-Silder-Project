#/bin/bash
rm -rf ./submission/*
#mkdir ./submission/src
cp -p $(find ./src -name "*.java") ./submission/
#cp -a ./src ./submission/src
cp comments.txt ./submission
cp agent.txt ./submission
cp ./out/production/AI-Shreya/META-INF/MANIFEST.MF ./submission/manifest.mf
