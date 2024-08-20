

docker buildx build --platform linux/amd64 --load -t graph-project-amd64:0.0.1 .
docker buildx build --platform linux/arm64 --load -t graph-project-arm64:0.0.1 .



