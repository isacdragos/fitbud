import { Routes, Route } from 'react-router-dom';

import './App.css'
import AuthPage from './features/auth/pages/AuthPage';
import TestPage from './features/auth/pages/TestPage';
import WorkoutsPage from './features/workout/pages/WorkoutPage';

function App() {
  return (
    <Routes>
      <Route path="/" element={<AuthPage />} />
      <Route path="/auth" element={<AuthPage />} />
      <Route path="/test" element={<TestPage />} />
      <Route path="/workout" element={<WorkoutsPage />} />
    </Routes>
  );
}

export default App
