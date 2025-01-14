include .env/.env
export

compile:
	./compile.bat

clean:
	rm -rf ./build/*

run:
	java -jar ./build/MultiFloorHospital.jar