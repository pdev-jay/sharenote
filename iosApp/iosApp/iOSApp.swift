import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init(){
        AppModuleKt.iosInitKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}