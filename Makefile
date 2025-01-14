include .env/.dev
export

# run:
# 	java -cp ./build main.Main

test:
	java -cp ./build ForTests

compile:
	./compile.bat

clean:
	rm -rf ./build/*

run:
	java -jar ./build/MultiFloorHospital.jar