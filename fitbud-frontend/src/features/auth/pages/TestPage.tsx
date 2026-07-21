import { useEffect, useState } from "react";
import api from "../../../shared/services/api";

export default function TestPage() {
    const [message, setMessage] = useState("Loading...");

    useEffect(() => {
        async function load() {
            try {
                const response = await api.get("/test");
                setMessage(response.data);
            } catch {
                setMessage("Authentication failed.");
            }
        }

        load();
    }, []);

    return (
        <div className="min-h-screen bg-[#0d0f12] flex items-center justify-center text-white">
            <div className="text-center">
                <h1 className="text-4xl font-bold mb-6">
                    Test Page
                </h1>

                <p className="text-xl">
                    {message}
                </p>
            </div>
        </div>
    );
}