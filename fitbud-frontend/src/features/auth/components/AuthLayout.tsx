import type { ReactNode } from "react";

type AuthLayoutProps = {
    children: ReactNode;
};

export default function AuthLayout({ children }: AuthLayoutProps) {
    return (
        <div className="min-h-screen bg-[#0d0f12] flex items-center justify-center px-6 p-8">
            <div className="w-full max-w-md">

                <div className="flex flex-col items-center mb-10">

                    <div className="w-10 h-10 rounded-xl bg-lime-400 flex items-center justify-center font-bold text-black mb-1">
                        F
                    </div>

                    <h1 className="logo-font font-black text-7xl tracking-tight leading-none">
                        <span className="text-white">FIT</span>
                        <span className="text-lime-400">BUD</span>
                    </h1>

                    <p className="text-gray-400 mt-2">
                        Your ultimate fitness companion
                    </p>

                </div>

                {children}

            </div>
        </div>
    );
}