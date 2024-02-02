package kzhsw;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(batchSize = 2000, iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(value = 2)
public class S3EscaperBenchmark {

    public static String[] testStrings = {
        "my-bucket",
        "photos/2024/02/02",
        "file.txt?versionId=3L137",
        "user@domain.com",
        "document.pdf",
        "music/2024/album/song.mp3",
        "video/2024/movie.mp4?versionId=1A2B",
        "data/archive.zip",
        "application/database.sqlite",
        "folder/subfolder/file.txt",
        "special-characters/!$&'()*+,/:;=@[]",
        "emoji/😀😃😄😁😆😅😂🤣",
        "non-english/こんにちは/안녕하세요"
    };

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void benchmarkEncode() {
        for (String testString : testStrings) {
            S3Escaper.encode(testString);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void benchmarkEncodeOptimized() {
        for (String testString : testStrings) {
            S3Escaper.encodeOptimized(testString);
        }
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
