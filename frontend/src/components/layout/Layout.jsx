import { Outlet } from 'react-router-dom';
import { Navbar } from '../Navbar';

export function Layout() {
    return (
        <div className="min-h-screen flex flex-col">
            <Navbar />
            <main className="flex-1 max-w-7xl mx-auto w-full px-4 py-8">
                <Outlet />
            </main>
            <footer className="bg-white border-t py-6">
                <div className="max-w-7xl mx-auto px-4 text-center text-gray-500">
                    © 2026 Tênis Mesa - Todos os direitos reservados
                </div>
            </footer>
        </div>
    );
}