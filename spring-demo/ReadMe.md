
# About bug reproduce, when run without IDE
After Step4 you can't see the stack trace of where pinned happen(it should be printed by Djdk.tracePinnedThreads VM option) and can't see "pinned ended!" printed at console also. But if you run step4 after running step3 without -javaagent:lib/dd-java-agent-1.49.0.jar, you can see the stack trace where pinned happened.

If you see thread dump made by jstack, you can see dd-telemetry thread's state changed after pinned happen(This was the main reason why i thought dd-java-agent might be the problem and tried to remove it.
However when reproducing one with dropwizard framework(our company uses it), i found dd-trace-monitor's state changed. Supposing there might be another reason that make this. The one thing i can say is that dd-java-agent.jar is making this issue.

[spring_demo_jstack_before_pinned_2025-05-19_10-43-37.txt](loganddumps%2Fspring_demo_jstack_before_pinned_2025-05-19_10-43-37.txt)
[spring_demo_after_pinned_jstack_2025-05-19_10-43-42.txt](loganddumps%2Fspring_demo_after_pinned_jstack_2025-05-19_10-43-42.txt)

#### Step 1
```
docker run --rm\
  -p 8126:8126\
  -e SNAPSHOT_CI=0\
  -v $PWD/tests/snapshots:/snapshots\
  ghcr.io/datadog/dd-apm-test-agent/ddapm-test-agent:latest
```

#### Step 1
`./gradlew clean build`

#### Step 3
`java -jar -javaagent:lib/dd-java-agent-1.49.0.jar -Djdk.tracePinnedThreads=full build/libs/demo-0.0.1-SNAPSHOT.jar`

#### Step 4
`curl http://localhost:8080/pinned/test`

# About bug reproduce, when run with Intellij IDE
After step2 you can get the log below. Saying SIGSEGV has been received. If you look in to the hs_err log you can see the stack trace ended at `V  [libjvm.dylib+0x482d08]  AccessInternal::PostRuntimeDispatch<G1BarrierSet::AccessBarrier<548964ull, G1BarrierSet>, (AccessInternal::BarrierType)2, 548964ull>::oop_access_barrier(void*)+0x8` which might cause `SIGSEGV` occur.

[hs_err_pid38323.log](loganddumps%2Fhs_err_pid38323.log)
![screenshot.png](loganddumps%2Fscreenshot.png)

#### Step 1
```
docker run --rm\
  -p 8126:8126\
  -e SNAPSHOT_CI=0\
  -v $PWD/tests/snapshots:/snapshots\
  ghcr.io/datadog/dd-apm-test-agent/ddapm-test-agent:latest
```

#### Step 2
Run `DemoApplication` with ide
