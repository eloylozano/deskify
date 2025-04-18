<script>
	import Nav from '../../components/Nav.svelte';
	import FaqList from '../../components/FaqList.svelte';

	let faqs = [
		{
			question: 'How do I register?',
			answer:
				"You can register by clicking on the 'Register' button located at the top right corner of the homepage. You will be redirected to a registration form where you'll need to enter your name, email address, and a secure password. Once you've filled in your details, click 'Submit' and check your email for a confirmation link to activate your account.",
			image: '/images/faq/register.png'
		},
		{
			question: "What is the platform's main feature?",
			answer:
				'Our platform provides real-time communication and task collaboration for teams of all sizes. With features like instant messaging, file sharing, video conferencing, and integrated calendars, users can manage their projects more efficiently and stay connected regardless of their location.',
			image: '/images/faq/feature.png'
		},
		{
			question: 'How do I change my password?',
			answer:
				"To change your password, log into your account and go to the 'Settings' page by clicking on your profile icon. Under the 'Security' tab, you'll find the option to update your password. Enter your current password, then type your new one and confirm it. Don't forget to click 'Save Changes' to apply the update.",
			image: '/images/faq/change-password.png'
		},
		{
			question: 'Can I invite other users to the platform?',
			answer:
				"Yes, you can invite team members by going to the 'Team' section in your dashboard. Click on 'Invite New Member', then enter their email address and assign them a role (Admin, Manager, Member, etc.). They will receive an invitation email to join your workspace.",
			image: '/images/faq/invite-users.png'
		},
		{
			question: 'Is there a mobile app available?',
			answer:
				'Absolutely! Our mobile app is available for both iOS and Android. You can download it from the App Store or Google Play. The app offers most of the desktop features, including messaging, notifications, file access, and video calls. It’s designed to keep you productive on the go.',
			image: '/images/faq/mobile-app.png'
		},
		{
			question: 'How do I upgrade my plan?',
			answer:
				"To upgrade your plan, go to 'Billing' under your account settings. You will see the current plan you're subscribed to and available upgrade options. Choose the plan that best fits your needs and follow the instructions to complete the payment securely. Your account will be updated instantly.",
			image: '/images/faq/upgrade.png'
		},
		{
			question: 'What should I do if I encounter a technical issue?',
			answer:
				"If you run into a technical issue, first try refreshing the page or restarting the app. If the problem persists, visit our 'Help Center' or contact our support team via the 'Support' section in your dashboard. You can submit a ticket detailing your issue, and our team will get back to you within 24 hours.",
			image: '/images/faq/support.png'
		},
		{
			question: 'Is my data safe and secure on this platform?',
			answer:
				'Yes, your data security is our top priority. We use end-to-end encryption, secure cloud storage, and follow industry best practices to ensure all your information remains confidential. Additionally, regular backups and system audits are performed to maintain a high level of protection.',
			image: '/images/faq/security.png'
		}
	];
</script>

<div class="flex h-screen bg-emerald-200">
	<Nav />

	<div class="flex-1 overflow-y-auto">
		<FaqList {faqs} />
	</div>
</div>
