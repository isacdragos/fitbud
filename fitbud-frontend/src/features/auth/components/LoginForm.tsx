import { useState } from "react";
import { login } from "../services/authService";
import { useNavigate } from "react-router-dom";
import axios from "axios";


export default function LoginForm() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const [error, setError] = useState("");

    async function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        console.log("Email:", email);
        console.log("Password:", password);

        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            console.log("Invalid email");
            setError("Please enter a valid email address.");
            return;
        }

        try {
            const response = await login({email, password});
            localStorage.setItem("token", response.token);

            navigate("/workout");
            console.log(response.token);
            setError("");
            
        } catch (error) {
            console.error("An error occurred:", error);
            if (axios.isAxiosError(error)) {
                setError(error.response?.data ?? "Something went wrong.");
            } else {
                setError("Something went wrong.");
            }
        }
    }

    return (
    <form onSubmit={handleSubmit} className="space-y-4 noValidate">

    <div>

        <label className="block mb-2 text-[11px] tracking-[2px] text-gray-400">
            EMAIL
        </label>

        <input
            type="email"
            placeholder="alex@example.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="
                w-full
                rounded-lg
                bg-[#1b1f25]
                border
                border-[#2b3037]
                px-4
                py-3
                text-white
                text-sm
                placeholder:text-gray-500
                outline-none
                transition-all
                duration-300
                ease-out
                focus:border-lime-400
            "
        />

    </div>

    <div>

        <label className="block mb-2 text-[11px] tracking-[2px] text-gray-400">
            PASSWORD
        </label>

        <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="
                w-full
                rounded-lg
                bg-[#1b1f25]
                border
                border-[#2b3037]
                px-4
                py-3
                text-white
                text-sm
                placeholder:text-gray-500
                outline-none
                transition-all
                duration-300
                ease-out
                focus:border-lime-400
            "
        />

    </div>

    {error && (
    <div
        className="
            rounded-xl
            border
            border-red-500/40
            bg-red-500/10
            px-4
            py-3
            text-sm
            text-red-400
        "
    >
        {error}
    </div>
)}
    
    <div className="mt-7">
        <button
            type="submit"
            className="
                logo-font
                text-xl
                w-full
                rounded-lg
                bg-lime-400
                py-3
                text-sm
                font-bold
                text-black
                transition
                cursor-pointer
                transition-all
                duration-300
                hover:brightness-110
                hover:shadow-[0_0_15px_rgba(163,230,53,0.55)]
                hover:scale-[1.02]
            "
        >
            LET'S GO →
        </button>
    </div>

    <p className="text-center text-gray-500 text-sm">

        Don't have an account?

        <button
            type="button"
            className="ml-2 text-lime-400 font-semibold hover:underline"
        >
            Sign up free
        </button>

    </p>

</form>
);
}