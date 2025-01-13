include .env/.dev
export

run:
	java -cp ./build main.MultiFloorHospital

test:
	java -cp ./build ForTests

compile:
	./compile.bat

clean:
	rm -rf ./build/*